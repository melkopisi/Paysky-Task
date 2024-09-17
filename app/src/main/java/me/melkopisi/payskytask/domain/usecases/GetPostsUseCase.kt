package me.melkopisi.payskytask.domain.usecases

import kotlinx.coroutines.flow.Flow
import me.melkopisi.payskytask.domain.remote.datasource.RemoteDS
import me.melkopisi.payskytask.domain.remote.models.posts.PostsModel
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(private val remoteDS: RemoteDS) {
    suspend operator fun invoke(): Flow<List<PostsModel>> = remoteDS.getPosts()

}