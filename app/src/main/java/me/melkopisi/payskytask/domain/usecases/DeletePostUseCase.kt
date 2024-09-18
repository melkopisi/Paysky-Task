package me.melkopisi.payskytask.domain.usecases

import kotlinx.coroutines.flow.map
import me.melkopisi.payskytask.domain.repositories.PostsRepository
import javax.inject.Inject

class DeletePostUseCase @Inject constructor(private val repository: PostsRepository) {
    suspend operator fun invoke(postId: Int) = repository.deletePost(postId).map { posts -> posts.map { it.toModel() } }

}