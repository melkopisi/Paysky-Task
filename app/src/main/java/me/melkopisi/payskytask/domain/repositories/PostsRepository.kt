package me.melkopisi.payskytask.domain.repositories

import kotlinx.coroutines.flow.Flow
import me.melkopisi.payskytask.data.local.entity.PostEntity

interface PostsRepository {
    suspend fun getPosts(): Flow<List<PostEntity>>
    suspend fun addPost(post: PostEntity): Flow<List<PostEntity>>
    suspend fun updatePost(postId: Int, post: PostEntity): Flow<List<PostEntity>>
    suspend fun deletePost(postId: Int): Flow<List<PostEntity>>
}