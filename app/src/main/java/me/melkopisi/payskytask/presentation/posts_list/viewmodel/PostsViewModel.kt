package me.melkopisi.payskytask.presentation.posts_list.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import me.melkopisi.payskytask.core.SingleLiveEvent
import me.melkopisi.payskytask.domain.usecases.AddPostUseCase
import me.melkopisi.payskytask.domain.usecases.DeletePostUseCase
import me.melkopisi.payskytask.domain.usecases.GetPostsUseCase
import me.melkopisi.payskytask.domain.usecases.UpdatePostUseCase
import me.melkopisi.payskytask.presentation.posts_list.adapter.models.PostsUiModel
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase,
    private val addPostUseCase: AddPostUseCase,
    private val updatePostUseCase: UpdatePostUseCase,
    private val deletePostUseCase: DeletePostUseCase
) : ViewModel() {
    private val _screenState = SingleLiveEvent<ScreenState>()
    val screenState: LiveData<ScreenState> = _screenState

    fun getPosts() {
        _screenState.value = ScreenState.Loading
        viewModelScope.launch {
            getPostsUseCase()
                .catch { throwable ->
                    ScreenState.Failure(throwable.message)
                }
                .collect { posts ->
                    _screenState.value = ScreenState.GetPostsSuccess(posts.map { it.toUiModel() })
                }
        }
    }

    fun addPost(post: PostsUiModel) {
        _screenState.value = ScreenState.Loading
        viewModelScope.launch {
            addPostUseCase(post.toModel())
                .catch { throwable ->
                    ScreenState.Failure(throwable.message)
                }
                .collect { post ->
                    _screenState.value = ScreenState.AddPostSuccess(post.toUiModel())
                }
        }
    }

    fun updatePost(postId: Int, post: PostsUiModel) {
        _screenState.value = ScreenState.Loading
        viewModelScope.launch {
            updatePostUseCase(postId, post.toModel())
                .catch { throwable ->
                    ScreenState.Failure(throwable.message)
                }
                .collect { post ->
                    _screenState.value = ScreenState.UpdatePostSuccess(post.toUiModel())
                }
        }
    }

    fun deletePost(postId: Int) {
        _screenState.value = ScreenState.Loading
        viewModelScope.launch {
            deletePostUseCase(postId)
                .catch { throwable ->
                    ScreenState.Failure(throwable.message)
                }
                .collect {
                    ScreenState.DeletePostSuccess(postId)
                }
        }
    }


    sealed interface ScreenState {
        object Loading : ScreenState
        data class GetPostsSuccess(val posts: List<PostsUiModel>) : ScreenState
        data class AddPostSuccess(val post: PostsUiModel) : ScreenState
        data class UpdatePostSuccess(val post: PostsUiModel) : ScreenState
        data class DeletePostSuccess(val postId: Int) : ScreenState
        data class Failure(val message: String?) : ScreenState
    }
}