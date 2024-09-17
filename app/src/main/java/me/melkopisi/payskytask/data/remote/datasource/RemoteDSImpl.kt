package me.melkopisi.payskytask.data.remote.datasource

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import me.melkopisi.payskytask.core.extensions.parseResponse
import me.melkopisi.payskytask.data.remote.models.posts.PostsResponse
import me.melkopisi.payskytask.data.remote.network.ApiService
import me.melkopisi.payskytask.domain.remote.datasource.RemoteDS
import me.melkopisi.payskytask.domain.remote.models.posts.PostsModel
import javax.inject.Inject

class RemoteDSImpl @Inject constructor(
    private val apiService: ApiService
) : RemoteDS {
    override suspend fun getPosts(): Flow<List<PostsModel>> = flow {
        emit(apiService.getPosts())
    }.parseResponse().map { list -> list.map { it.toModel() } }

    override suspend fun addPost(post: PostsResponse): Flow<PostsModel> = flow {
        emit(apiService.addPost(post))
    }.parseResponse().map { it.toModel() }

    override suspend fun updatePost(id: Int, post: PostsResponse): Flow<PostsModel> =
        flow { emit(apiService.updatePost(id, post)) }.parseResponse().map { it.toModel() }

    override suspend fun deletePost(id: Int) = flow {
        emit(apiService.deletePost(id))
    }.parseResponse()
}