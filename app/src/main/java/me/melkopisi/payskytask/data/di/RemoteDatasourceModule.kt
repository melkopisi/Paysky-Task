package me.melkopisi.payskytask.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.melkopisi.payskytask.data.remote.datasource.RemoteDSImpl
import me.melkopisi.payskytask.domain.remote.datasource.RemoteDS

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteDatasourceModule {
    @Binds
    abstract fun bindRemoteDataSource(remoteDS: RemoteDSImpl): RemoteDS

}