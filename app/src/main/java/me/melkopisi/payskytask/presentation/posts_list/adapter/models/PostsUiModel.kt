package me.melkopisi.payskytask.presentation.posts_list.adapter.models

import me.melkopisi.payskytask.domain.remote.models.posts.PostsModel

data class PostsUiModel(
    val id: Int,
    val title: String,
    val desc: String
) {
    fun toModel() = PostsModel(id, title, desc)
}
