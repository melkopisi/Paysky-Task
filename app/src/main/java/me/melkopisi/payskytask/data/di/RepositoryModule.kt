package me.melkopisi.payskytask.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.melkopisi.payskytask.data.repositories.PostsRepositoryImpl
import me.melkopisi.payskytask.domain.repositories.PostsRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindPostsRepository(postsRepository: PostsRepositoryImpl): PostsRepository

}