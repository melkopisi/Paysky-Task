package me.melkopisi.payskytask.domain.usecases

import kotlinx.coroutines.flow.Flow
import me.melkopisi.payskytask.domain.remote.datasource.RemoteDS
import me.melkopisi.payskytask.domain.remote.models.posts.PostsModel
import javax.inject.Inject

class AddPostUseCase @Inject constructor(private val remoteDS: RemoteDS) {
    suspend operator fun invoke(post: PostsModel): Flow<PostsModel> = remoteDS.addPost(post.toResponse())

}