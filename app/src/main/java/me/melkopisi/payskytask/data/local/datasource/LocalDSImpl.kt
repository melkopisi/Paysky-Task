package me.melkopisi.payskytask.data.local.datasource

import kotlinx.coroutines.flow.Flow
import me.melkopisi.payskytask.data.local.PostsDAO
import me.melkopisi.payskytask.data.local.entity.PostEntity
import me.melkopisi.payskytask.domain.local.datasource.LocalDS
import javax.inject.Inject

class LocalDSImpl @Inject constructor(
    private val dao: PostsDAO
) : LocalDS {

    override fun getPosts(): Flow<List<PostEntity>> = dao.getPosts()
    override suspend fun addPosts(post: List<PostEntity>) = dao.addPosts(post)
    override suspend fun addPost(post: PostEntity) = dao.addPost(post)
    override suspend fun updatePost(post: PostEntity) = dao.updatePost(post)
    override suspend fun deletePost(postId: Int) = dao.deletePost(postId)
}