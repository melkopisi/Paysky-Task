package me.melkopisi.payskytask.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.melkopisi.payskytask.data.local.datasource.LocalDSImpl
import me.melkopisi.payskytask.domain.local.datasource.LocalDS

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalDatasourceModule {
    @Binds
    abstract fun bindLocalDataSource(localDS: LocalDSImpl): LocalDS

}