package me.melkopisi.payskytask.data.di.local

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import me.melkopisi.payskytask.data.local.PayskyDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {


    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): PayskyDatabase {
        return Room
            .databaseBuilder(context, PayskyDatabase::class.java, DatabaseConstants.DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideLocalPostsDAO(appDatabase: PayskyDatabase) = appDatabase.postsDao()


}