package me.melkopisi.payskytask.presentation.posts_list.fragment

import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import me.melkopisi.payskytask.R
import me.melkopisi.payskytask.art_core.base.BaseFragment
import me.melkopisi.payskytask.art_core.extensions.onDone
import me.melkopisi.payskytask.art_core.extensions.setupDialog
import me.melkopisi.payskytask.art_core.extensions.showKeyboard
import me.melkopisi.payskytask.databinding.DialogPostOperationsBinding
import me.melkopisi.payskytask.databinding.FragmentPostsListBinding
import me.melkopisi.payskytask.presentation.posts_list.adapter.PostAdapter
import me.melkopisi.payskytask.presentation.posts_list.adapter.models.PostsUiModel
import me.melkopisi.payskytask.presentation.posts_list.viewmodel.PostsViewModel
import timber.log.Timber

@AndroidEntryPoint
class PostsListFragment : BaseFragment<FragmentPostsListBinding>(FragmentPostsListBinding::inflate) {

    private val postAdapter by lazy { PostAdapter() }
    private val viewModel: PostsViewModel by viewModels()

    override fun setupViews() {
        binding.fabAddPost.setOnClickListener { setupAddPostDialog() }
        setPostsList()
        observeOnScreenState()
        viewModel.getPosts()

    }

    private fun observeOnScreenState() {
        viewModel.screenState.observe(viewLifecycleOwner, this::observeOnPosts)
    }

    private fun observeOnPosts(state: PostsViewModel.ScreenState) {
        when (state) {
            is PostsViewModel.ScreenState.Loading -> Toast.makeText(requireContext(), "Loading", Toast.LENGTH_SHORT).show()
            is PostsViewModel.ScreenState.AddPostSuccess -> TODO()
            is PostsViewModel.ScreenState.DeletePostSuccess -> TODO()
            is PostsViewModel.ScreenState.Failure -> Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
            is PostsViewModel.ScreenState.GetPostsSuccess -> postAdapter.submitList(state.posts)
            is PostsViewModel.ScreenState.UpdatePostSuccess -> TODO()
        }
    }

    private fun setPostsList() {
        binding.rvPosts.adapter = postAdapter
        postAdapter.onItemEdit {
            setupEditPostDialog(it)
            Timber.e("item edited $it")
        }

        postAdapter.onItemDelete {
            Timber.e("item deleted $it")
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

    @Suppress("DEPRECATION")
    private fun setupEditPostDialog(postsUiModel: PostsUiModel?) {
        val postOperationsDialogBinding = DialogPostOperationsBinding.inflate(layoutInflater)

        requireContext().setupDialog(postOperationsDialogBinding.root) { dialog ->
            with(postOperationsDialogBinding) {
                etTitle.setText(postsUiModel?.title)
                etDesc.setText(postsUiModel?.desc)
                tvTitle.text = getString(R.string.edit_post)
                btnPositive.text = getString(R.string.edit_post)
                etDesc.onDone { btnPositive.performClick() }
                btnPositive.setOnClickListener {

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