package me.melkopisi.payskytask.presentation.posts_list.fragment

import android.view.WindowManager
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import me.melkopisi.payskytask.R
import me.melkopisi.payskytask.art_core.base.BaseFragment
import me.melkopisi.payskytask.art_core.extensions.onDone
import me.melkopisi.payskytask.art_core.extensions.setupDialog
import me.melkopisi.payskytask.art_core.extensions.showKeyboard
import me.melkopisi.payskytask.databinding.DialogPostOperationsBinding
import me.melkopisi.payskytask.databinding.FragmentPostsListBinding
import me.melkopisi.payskytask.presentation.posts_list.adapter.PostAdapter
import me.melkopisi.payskytask.presentation.posts_list.adapter.models.PostUiModel
import me.melkopisi.payskytask.presentation.posts_list.viewmodel.PostsViewModel
import timber.log.Timber

@AndroidEntryPoint
class PostsListFragment : BaseFragment<FragmentPostsListBinding>(FragmentPostsListBinding::inflate) {

    private val postAdapter by lazy { PostAdapter() }
    private val viewModel: PostsViewModel by viewModels()

    override fun setupViews() {
        binding.fabAddPost.setOnClickListener { setupAddPostDialog() }
        setPostsRecyclerView()
        observeOnScreenState()
        viewModel.getPosts()

    }

    private fun observeOnScreenState() {
        viewModel.screenState.observe(viewLifecycleOwner, this::observeOnPosts)
    }

    private fun observeOnPosts(state: PostsViewModel.ScreenState) {
        when (state) {
            is PostsViewModel.ScreenState.Loading -> binding.progressCircular.isVisible = true
            is PostsViewModel.ScreenState.GetPostsSuccess -> {
                binding.srPosts.isRefreshing = false
                binding.progressCircular.isVisible = false
                postAdapter.submitList(state.posts)
            }

            is PostsViewModel.ScreenState.PostAddedSuccessfully -> {
                binding.srPosts.isRefreshing = false
                binding.progressCircular.isVisible = false
                postAdapter.submitList(state.posts)
                binding.root.post { binding.rvPosts.scrollToPosition(postAdapter.itemCount) }
            }

            is PostsViewModel.ScreenState.Failure -> {
                binding.srPosts.isRefreshing = false
                binding.progressCircular.isVisible = false
                Timber.e("error is ${state.message}")
                Snackbar.make(binding.root, state.message.orEmpty(), Snackbar.LENGTH_INDEFINITE).setAction("retry") {
                    viewModel.getPosts()
                }.show()
            }
        }
    }

    private fun setPostsRecyclerView() {
        binding.srPosts.setOnRefreshListener {
            viewModel.getPosts()
        }
        binding.rvPosts.adapter = postAdapter
        postAdapter.onItemEdit { post ->
            post?.let { setupEditPostDialog(it) }
        }

        postAdapter.onItemDelete { post ->
            post?.let { viewModel.deletePost(it.id) }
        }
    }

    @Suppress("DEPRECATION")
    private fun setupAddPostDialog() {
        val postOperationsDialogBinding = DialogPostOperationsBinding.inflate(layoutInflater)

        requireContext().setupDialog(postOperationsDialogBinding.root) { dialog ->
            with(postOperationsDialogBinding) {
                tvTitle.text = getString(R.string.add_post)
                btnPositive.text = getString(R.string.add_post)
                etDesc.onDone { btnPositive.performClick() }
                btnPositive.setOnClickListener {
                    viewModel.addPost(PostUiModel(postAdapter.itemCount.plus(1), etTitle.text.toString(), etDesc.text.toString()))
                    dialog.dismiss()

                }
                btnCancel.setOnClickListener { dialog.dismiss() }
            }

        }.also {
            it.setCancelable(true)
            it.setCanceledOnTouchOutside(true)
            it.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
            postOperationsDialogBinding.etTitle.showKeyboard()
            it.show()
        }

    }

    @Suppress("DEPRECATION")
    private fun setupEditPostDialog(postUiModel: PostUiModel) {
        val postOperationsDialogBinding = DialogPostOperationsBinding.inflate(layoutInflater)

        requireContext().setupDialog(postOperationsDialogBinding.root) { dialog ->
            with(postOperationsDialogBinding) {
                etTitle.setText(postUiModel.title)
                etDesc.setText(postUiModel.desc)
                tvTitle.text = getString(R.string.edit_post)
                btnPositive.text = getString(R.string.edit_post)
                etDesc.onDone { btnPositive.performClick() }
                btnPositive.setOnClickListener {
                    viewModel.updatePost(postUiModel.id, postUiModel.copy(title = etTitle.text.toString(), desc = etDesc.text.toString()))
                    //todo add post
                    dialog.dismiss()

                }
                btnCancel.setOnClickListener { dialog.dismiss() }
            }

        }.also {
            it.setCancelable(true)
            it.setCanceledOnTouchOutside(true)
            it.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
            postOperationsDialogBinding.etTitle.showKeyboard()
            it.show()
        }
    }


}