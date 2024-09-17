package me.melkopisi.payskytask.presentation.posts_list.fragment

import android.view.WindowManager
import me.melkopisi.payskytask.R
import me.melkopisi.payskytask.art_core.base.BaseFragment
import me.melkopisi.payskytask.art_core.extensions.onDone
import me.melkopisi.payskytask.art_core.extensions.setupDialog
import me.melkopisi.payskytask.art_core.extensions.showKeyboard
import me.melkopisi.payskytask.databinding.DialogPostOperationsBinding
import me.melkopisi.payskytask.databinding.FragmentPostsListBinding
import me.melkopisi.payskytask.presentation.posts_list.adapter.PostAdapter
import me.melkopisi.payskytask.presentation.posts_list.adapter.models.PostUiModel
import timber.log.Timber


class PostsListFragment : BaseFragment<FragmentPostsListBinding>(FragmentPostsListBinding::inflate) {

    private val postAdapter by lazy { PostAdapter() }

    private val postItems by lazy {
        mutableListOf<PostUiModel>().apply {
            add(PostUiModel(1, "Title 1", "Desc 1"))
            add(PostUiModel(2, "Title 2", "Desc 2"))
            add(PostUiModel(3, "Title 3", "Desc 3"))
            add(
                PostUiModel(
                    4,
                    "Title 4",
                    "Desc 4 long description 3 lines kjfdskljdlskfjasdlkfjsdlfkjsdflksdjfskldfjskldfjsdklfjsfkljsdkljsdalkfjsadfkljsdafklsadjfklasdjfaslkdfj "
                )
            )
            add(PostUiModel(5, "Title 5", "Desc 5"))
            add(PostUiModel(6, "Title 6", "Desc 6"))
            add(PostUiModel(7, "Title 7", "Desc 7"))

        }
    }

    override fun setupViews() {
        binding.fabAddPost.setOnClickListener { setupAddPostDialog() }
        populatePosts()

    }

    private fun populatePosts() {
        binding.rvPosts.adapter = postAdapter
        postAdapter.submitList(postItems)
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
    private fun setupEditPostDialog(postUiModel: PostUiModel?) {
        val postOperationsDialogBinding = DialogPostOperationsBinding.inflate(layoutInflater)

        requireContext().setupDialog(postOperationsDialogBinding.root) { dialog ->
            with(postOperationsDialogBinding) {
                etTitle.setText(postUiModel?.title)
                etDesc.setText(postUiModel?.desc)
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