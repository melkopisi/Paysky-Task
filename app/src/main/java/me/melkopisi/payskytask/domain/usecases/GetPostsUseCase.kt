package me.melkopisi.payskytask.domain.usecases

import kotlinx.coroutines.flow.map
import me.melkopisi.payskytask.domain.repositories.PostsRepository
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(private val repository: PostsRepository) {
    suspend operator fun invoke() = repository.getPosts().map { posts -> posts.map { it.toModel() } }

}