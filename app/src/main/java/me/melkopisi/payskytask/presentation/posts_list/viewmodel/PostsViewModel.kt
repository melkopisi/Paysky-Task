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
import me.melkopisi.payskytask.presentation.posts_list.adapter.models.PostUiModel
import timber.log.Timber
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
                    _screenState.value = ScreenState.Failure(throwable.message)
                    Timber.e(throwable.message)
                }
                .collect { posts ->
                    Timber.e("posts size is ${posts.size}")
                    _screenState.value = ScreenState.GetPostsSuccess(posts.map { it.toUiModel() })
                }
        }
    }

    fun addPost(post: PostUiModel) {
        _screenState.value = ScreenState.Loading
        viewModelScope.launch {
            addPostUseCase(post.toModel())
                .catch { throwable ->
                    _screenState.value = ScreenState.Failure(throwable.message)
                }
                .collect { posts ->
                    _screenState.value = ScreenState.PostAddedSuccessfully(posts.map { it.toUiModel() })
                }
        }
    }

    fun updatePost(postId: Int, post: PostUiModel) {
        _screenState.value = ScreenState.Loading
        viewModelScope.launch {
            updatePostUseCase(postId, post.toModel())
                .catch { throwable ->
                    _screenState.value = ScreenState.Failure(throwable.message)
                }
                .collect { posts ->
                    _screenState.value = ScreenState.GetPostsSuccess(posts.map { it.toUiModel() })
                }
        }
    }

    fun deletePost(postId: Int) {
        _screenState.value = ScreenState.Loading
        viewModelScope.launch {
            deletePostUseCase(postId)
                .catch { throwable ->
                    _screenState.value = ScreenState.Failure(throwable.message)
                }
                .collect { posts ->
                    _screenState.value = ScreenState.GetPostsSuccess(posts.map { it.toUiModel() })
                }
        }
    }


    sealed interface ScreenState {
        object Loading : ScreenState
        data class GetPostsSuccess(val posts: List<PostUiModel>) : ScreenState
        data class PostAddedSuccessfully(val posts: List<PostUiModel>) : ScreenState
        data class Failure(val message: String?) : ScreenState
    }
}