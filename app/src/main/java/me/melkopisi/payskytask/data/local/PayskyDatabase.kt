package me.melkopisi.payskytask.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import me.melkopisi.payskytask.data.di.local.DatabaseConstants
import me.melkopisi.payskytask.data.local.entity.PostEntity


@Database(
    entities = [
        PostEntity::class,
    ],
    version = DatabaseConstants.DB_VERSION,
    exportSchema = false,
)
abstract class PayskyDatabase : RoomDatabase() {
    abstract fun postsDao(): PostsDAO
}