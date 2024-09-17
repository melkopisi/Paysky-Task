package me.melkopisi.payskytask.domain.usecases

import me.melkopisi.payskytask.domain.remote.datasource.RemoteDS
import javax.inject.Inject

class DeletePostUseCase @Inject constructor(private val remoteDS: RemoteDS) {
    suspend operator fun invoke(postId: Int) = remoteDS.deletePost(postId)

}