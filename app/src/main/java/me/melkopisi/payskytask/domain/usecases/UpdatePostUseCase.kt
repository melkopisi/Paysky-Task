package me.melkopisi.payskytask.domain.usecases

import kotlinx.coroutines.flow.map
import me.melkopisi.payskytask.domain.remote.models.posts.PostsModel
import me.melkopisi.payskytask.domain.repositories.PostsRepository
import javax.inject.Inject

class UpdatePostUseCase @Inject constructor(private val repository: PostsRepository) {
    suspend operator fun invoke(postId: Int, post: PostsModel) =
        repository.updatePost(postId, post.toEntity()).map { posts -> posts.map { it.toModel() } }

}