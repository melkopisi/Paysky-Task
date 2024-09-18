package me.melkopisi.payskytask.presentation.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import me.melkopisi.payskytask.domain.repositories.PostsRepository
import timber.log.Timber

@HiltWorker
class SyncPostsWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val repository: PostsRepository
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        Timber.e("SyncWorker: doWork")
        return try {
            repository.getPosts().collect {}
            Timber.e("SyncWorker: doWork: success")
            Result.success()
        } catch (e: Exception) {
            Timber.e("SyncWorker: doWork: retry")
            Result.retry()
        }
    }
}
