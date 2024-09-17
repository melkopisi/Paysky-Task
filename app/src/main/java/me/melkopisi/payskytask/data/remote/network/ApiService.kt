package me.melkopisi.payskytask.data.remote.network

import me.melkopisi.payskytask.data.remote.models.posts.PostsResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {

    companion object {
        const val POSTS = "posts"
    }

    @GET(POSTS)
    suspend fun getPosts(): Response<List<PostsResponse>>

    @POST("posts")
    suspend fun addPost(@Body post: PostsResponse): Response<PostsResponse>

    @PUT("posts/{id}")
    suspend fun updatePost(@Path("id") id: Int, @Body post: PostsResponse): Response<PostsResponse>

    @DELETE("posts/{id}")
    suspend fun deletePost(@Path("id") id: Int): Response<Unit>
}