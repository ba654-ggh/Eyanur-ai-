package com.example.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "saved_questions")
data class SavedQuestionEntity(
    @PrimaryKey
    val id: String,
    val questionEn: String,
    val questionHi: String,
    val answerEn: String,
    val answerHi: String,
    val categoryName: String,
    val difficulty: String,
    val keyTakeawayEn: String,
    val keyTakeawayHi: String,
    val funFactEn: String,
    val funFactHi: String,
    val tagsString: String,
    val isBookmarked: Boolean,
    val isCustomUserQuestion: Boolean,
    val timestamp: Long
)
