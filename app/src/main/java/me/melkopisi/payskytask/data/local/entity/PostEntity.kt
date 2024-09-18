package me.melkopisi.payskytask.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import me.melkopisi.payskytask.data.remote.models.posts.PostsResponse
import me.melkopisi.payskytask.domain.remote.models.posts.PostsModel

@Entity(tableName = "posts")

data class PostEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val body: String,

    ) {
    fun toResponse() = PostsResponse(id, title, body)
    fun toModel() = PostsModel(id, title, body)
}