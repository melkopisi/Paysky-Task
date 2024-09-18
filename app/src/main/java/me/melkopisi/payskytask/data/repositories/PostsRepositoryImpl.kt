package me.melkopisi.payskytask.data.repositories

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import me.melkopisi.payskytask.core.extensions.isInternetAvailable
import me.melkopisi.payskytask.data.local.entity.PostEntity
import me.melkopisi.payskytask.domain.local.datasource.LocalDS
import me.melkopisi.payskytask.domain.remote.datasource.RemoteDS
import me.melkopisi.payskytask.domain.remote.exceptions.Exceptions
import me.melkopisi.payskytask.domain.repositories.PostsRepository
import javax.inject.Inject

class PostsRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val remoteDS: RemoteDS,
    private val localDS: LocalDS
) : PostsRepository {

    override suspend fun getPosts(): Flow<List<PostEntity>> = callbackFlow {
        if (context.isInternetAvailable()) remoteDS.getPosts().collect { posts ->
            localDS.addPosts(posts.map { it.toEntity() })
        }
        localDS.getPosts().collect {
            if (it.isEmpty()) throw Exceptions.NoLocalDataException() else trySend(it)
        }
    }

    override suspend fun addPost(post: PostEntity): Flow<List<PostEntity>> = callbackFlow {
        if (context.isInternetAvailable()) remoteDS.addPost(post.toResponse()).collect {
            localDS.addPost(it.toEntity())
        } else {
            localDS.addPost(post)
        }
        localDS.getPosts().collect {
            if (it.isEmpty()) throw Exceptions.NoLocalDataException() else trySend(it)
        }
    }

    override suspend fun updatePost(postId: Int, post: PostEntity): Flow<List<PostEntity>> = callbackFlow {
        if (context.isInternetAvailable()) remoteDS.updatePost(postId, post.toResponse()).collect {
            localDS.updatePost(post)
        } else {
            localDS.updatePost(post)
        }
        localDS.getPosts().collect {
            if (it.isEmpty()) throw Exceptions.NoLocalDataException() else trySend(it)
        }
    }

    override suspend fun deletePost(postId: Int): Flow<List<PostEntity>> = callbackFlow {
        if (context.isInternetAvailable())
            remoteDS.deletePost(postId).collect {
                localDS.deletePost(postId)
            } else {
            localDS.deletePost(postId)
        }
        localDS.getPosts().collect {
            if (it.isEmpty()) throw Exceptions.NoLocalDataException() else trySend(it)
        }
    }
}