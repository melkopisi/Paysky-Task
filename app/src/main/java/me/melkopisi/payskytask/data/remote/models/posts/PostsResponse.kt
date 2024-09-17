package me.melkopisi.payskytask.data.remote.models.posts


import com.google.gson.annotations.SerializedName
import me.melkopisi.payskytask.domain.remote.models.posts.PostsModel

data class PostsResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("body")
    val body: String
) {
    fun toModel() = PostsModel(id, title, body)

}