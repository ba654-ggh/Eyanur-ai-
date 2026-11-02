package com.example.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface QuestionDao {
    @Query("SELECT * FROM saved_questions WHERE isBookmarked = 1 ORDER BY timestamp DESC")
    fun getBookmarkedQuestions(): Flow<List<SavedQuestionEntity>>

    @Query("SELECT * FROM saved_questions WHERE isCustomUserQuestion = 1 ORDER BY timestamp DESC")
    fun getCustomQuestions(): Flow<List<SavedQuestionEntity>>

    @Query("SELECT * FROM saved_questions ORDER BY timestamp DESC")
    fun getAllSaved(): Flow<List<SavedQuestionEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(question: SavedQuestionEntity)

    @Query("DELETE FROM saved_questions WHERE id = :id")
    suspend fun deleteById(id: String)

    @Query("SELECT EXISTS(SELECT 1 FROM saved_questions WHERE id = :id AND isBookmarked = 1)")
    suspend fun isBookmarked(id: String): Boolean

    @Query("UPDATE saved_questions SET isBookmarked = :bookmarked WHERE id = :id")
    suspend fun updateBookmarkStatus(id: String, bookmarked: Boolean)
}
