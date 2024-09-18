package me.melkopisi.payskytask.domain.local.datasource

import kotlinx.coroutines.flow.Flow
import me.melkopisi.payskytask.data.local.entity.PostEntity

interface LocalDS {
    fun getPosts(): Flow<List<PostEntity>>
    suspend fun addPost(post: PostEntity)
    suspend fun addPosts(post: List<PostEntity>)
    suspend fun updatePost(post: PostEntity)
    suspend fun deletePost(postId: Int)
}