package me.melkopisi.payskytask.presentation.posts_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.kashier.art_core.base.BaseRecyclerAdapter
import com.kashier.art_core.base.BaseViewHolder
import me.melkopisi.payskytask.core.Consumer
import me.melkopisi.payskytask.databinding.ItemPostBinding
import me.melkopisi.payskytask.presentation.posts_list.adapter.models.PostsUiModel

class PostAdapter : BaseRecyclerAdapter<PostsUiModel, ItemPostBinding, PostAdapter.PostViewHolder>(object : DiffUtil.ItemCallback<PostsUiModel>() {
    override fun areItemsTheSame(oldItem: PostsUiModel, newItem: PostsUiModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PostsUiModel, newItem: PostsUiModel): Boolean {
        return oldItem == newItem
    }

}) {

    private var itemEditCallback: Consumer<PostsUiModel?>? = null
    private var itemDeleteCallback: Consumer<PostsUiModel?>? = null

    class PostViewHolder(
        private val binding: ItemPostBinding,
        private val editCallback: Consumer<PostsUiModel?>?,
        private val deleteCallback: Consumer<PostsUiModel?>?
    ) : BaseViewHolder<PostsUiModel, ItemPostBinding>(binding) {
        override fun bind() {
            with(binding) {

                tvTitle.text = getRowItem()?.title
                tvDesc.text = getRowItem()?.desc
                btnEdit.setOnClickListener { editCallback?.invoke(getRowItem()) }
                btnDelete.setOnClickListener { deleteCallback?.invoke(getRowItem()) }

            }
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder =
        PostViewHolder(ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false), itemEditCallback, itemDeleteCallback)

    fun onItemEdit(callback: Consumer<PostsUiModel?>?) {
        this.itemEditCallback = callback

    }

    fun onItemDelete(callback: Consumer<PostsUiModel?>?) {
        this.itemDeleteCallback = callback
    }
}