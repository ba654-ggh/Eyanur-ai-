package com.example.data.model

data class QuestionItem(
    val id: String,
    val questionEn: String,
    val questionHi: String,
    val answerEn: String,
    val answerHi: String,
    val category: QuestionCategory,
    val difficulty: String = "Medium", // Easy, Medium, Hard
    val keyTakeawayEn: String = "",
    val keyTakeawayHi: String = "",
    val funFactEn: String = "",
    val funFactHi: String = "",
    val tags: List<String> = emptyList(),
    val isBookmarked: Boolean = false,
    val isCustomUserQuestion: Boolean = false,
    val timestamp: Long = System.currentTimeMillis()
)
