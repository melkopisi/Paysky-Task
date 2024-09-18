package me.melkopisi.payskytask.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import me.melkopisi.payskytask.data.local.entity.PostEntity

@Dao
interface PostsDAO {
    @Query("SELECT * FROM posts")
    fun getPosts(): Flow<List<PostEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPosts(posts: List<PostEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPost(posts: PostEntity)

    @Update
    suspend fun updatePost(post: PostEntity)

    @Query("DELETE FROM posts WHERE id = :id")
    suspend fun deletePost(id: Int)


}