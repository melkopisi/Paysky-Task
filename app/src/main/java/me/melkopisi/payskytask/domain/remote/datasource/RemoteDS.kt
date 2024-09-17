package me.melkopisi.payskytask.domain.remote.datasource

import kotlinx.coroutines.flow.Flow
import me.melkopisi.payskytask.data.remote.models.posts.PostsResponse
import me.melkopisi.payskytask.domain.remote.models.posts.PostsModel

interface RemoteDS {

    suspend fun getPosts(): Flow<List<PostsModel>>
    suspend fun addPost(post: PostsResponse): Flow<PostsModel>
    suspend fun updatePost(id: Int, post: PostsResponse): Flow<PostsModel>
    suspend fun deletePost(id: Int): Flow<Unit>
}