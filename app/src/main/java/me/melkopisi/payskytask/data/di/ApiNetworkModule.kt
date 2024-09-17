package me.melkopisi.payskytask.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.melkopisi.payskytask.data.remote.network.ApiService
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiNetworkModule {

    @Singleton
    @Provides
    fun providesPosApi(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}