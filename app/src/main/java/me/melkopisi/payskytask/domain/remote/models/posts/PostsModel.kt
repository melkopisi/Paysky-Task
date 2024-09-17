package me.melkopisi.payskytask.domain.remote.models.posts

import me.melkopisi.payskytask.data.remote.models.posts.PostsResponse
import me.melkopisi.payskytask.presentation.posts_list.adapter.models.PostsUiModel


data class PostsModel(
    val id: Int,
    val title: String,
    val body: String,
) {
    fun toResponse() = PostsResponse(id, title, body)
    fun toUiModel() = PostsUiModel(id, title, body)
}