package me.melkopisi.payskytask.presentation.posts_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.kashier.art_core.base.BaseRecyclerAdapter
import com.kashier.art_core.base.BaseViewHolder
import me.melkopisi.payskytask.core.Consumer
import me.melkopisi.payskytask.databinding.ItemPostBinding
import me.melkopisi.payskytask.presentation.posts_list.adapter.models.PostUiModel

class PostAdapter : BaseRecyclerAdapter<PostUiModel, ItemPostBinding, PostAdapter.PostViewHolder>(object : DiffUtil.ItemCallback<PostUiModel>() {
    override fun areItemsTheSame(oldItem: PostUiModel, newItem: PostUiModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PostUiModel, newItem: PostUiModel): Boolean {
        return oldItem == newItem
    }

}) {

    private var itemEditCallback: Consumer<PostUiModel?>? = null
    private var itemDeleteCallback: Consumer<PostUiModel?>? = null

    class PostViewHolder(
        private val binding: ItemPostBinding,
        private val editCallback: Consumer<PostUiModel?>?,
        private val deleteCallback: Consumer<PostUiModel?>?
    ) : BaseViewHolder<PostUiModel, ItemPostBinding>(binding) {
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

    fun onItemEdit(callback: Consumer<PostUiModel?>?) {
        this.itemEditCallback = callback

    }

    fun onItemDelete(callback: Consumer<PostUiModel?>?) {
        this.itemDeleteCallback = callback
    }
}