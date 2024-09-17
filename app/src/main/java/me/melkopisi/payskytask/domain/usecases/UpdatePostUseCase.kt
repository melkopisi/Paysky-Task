package me.melkopisi.payskytask.domain.usecases

import kotlinx.coroutines.flow.Flow
import me.melkopisi.payskytask.domain.remote.datasource.RemoteDS
import me.melkopisi.payskytask.domain.remote.models.posts.PostsModel
import javax.inject.Inject

class UpdatePostUseCase @Inject constructor(private val remoteDS: RemoteDS) {
    suspend operator fun invoke(postId: Int, post: PostsModel): Flow<PostsModel> = remoteDS.updatePost(postId, post.toResponse())

}