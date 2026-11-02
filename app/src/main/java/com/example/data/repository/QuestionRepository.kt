package com.example.data.repository

import com.example.data.ai.GeminiAnswerService
import com.example.data.knowledge.KnowledgeBase
import com.example.data.local.QuestionDao
import com.example.data.local.SavedQuestionEntity
import com.example.data.model.QuestionCategory
import com.example.data.model.QuestionItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class QuestionRepository(
    private val questionDao: QuestionDao,
    private val geminiService: GeminiAnswerService = GeminiAnswerService()
) {

    val bookmarkedQuestions: Flow<List<QuestionItem>> = questionDao.getBookmarkedQuestions().map { entities ->
        entities.map { it.toQuestionItem() }
    }

    val customUserQuestions: Flow<List<QuestionItem>> = questionDao.getCustomQuestions().map { entities ->
        entities.map { it.toQuestionItem() }
    }

    fun getAllQuestions(category: QuestionCategory = QuestionCategory.ALL, searchQuery: String = ""): List<QuestionItem> {
        return KnowledgeBase.search(searchQuery, category)
    }

    fun getQuestionOfDay(): QuestionItem {
        return KnowledgeBase.getQuestionOfDay()
    }

    suspend fun isBookmarked(id: String): Boolean {
        return questionDao.isBookmarked(id)
    }

    suspend fun toggleBookmark(item: QuestionItem): Boolean {
        val currentlyBookmarked = questionDao.isBookmarked(item.id)
        val newStatus = !currentlyBookmarked
        val entity = item.copy(isBookmarked = newStatus).toEntity()
        questionDao.insertOrUpdate(entity)
        return newStatus
    }

    suspend fun askQuestion(query: String): QuestionItem {
        val answered = geminiService.answerQuestion(query)
        if (answered.isCustomUserQuestion) {
            questionDao.insertOrUpdate(answered.toEntity())
        }
        return answered
    }

    suspend fun saveCustomQuestion(item: QuestionItem) {
        questionDao.insertOrUpdate(item.toEntity())
    }

    suspend fun deleteSavedQuestion(id: String) {
        questionDao.deleteById(id)
    }

    private fun SavedQuestionEntity.toQuestionItem(): QuestionItem {
        val cat = try {
            QuestionCategory.valueOf(categoryName)
        } catch (_: Exception) {
            QuestionCategory.CURIOSITY
        }
        return QuestionItem(
            id = id,
            questionEn = questionEn,
            questionHi = questionHi,
            answerEn = answerEn,
            answerHi = answerHi,
            category = cat,
            difficulty = difficulty,
            keyTakeawayEn = keyTakeawayEn,
            keyTakeawayHi = keyTakeawayHi,
            funFactEn = funFactEn,
            funFactHi = funFactHi,
            tags = tagsString.split(",").filter { it.isNotBlank() },
            isBookmarked = isBookmarked,
            isCustomUserQuestion = isCustomUserQuestion,
            timestamp = timestamp
        )
    }

    private fun QuestionItem.toEntity(): SavedQuestionEntity {
        return SavedQuestionEntity(
            id = id,
            questionEn = questionEn,
            questionHi = questionHi,
            answerEn = answerEn,
            answerHi = answerHi,
            categoryName = category.name,
            difficulty = difficulty,
            keyTakeawayEn = keyTakeawayEn,
            keyTakeawayHi = keyTakeawayHi,
            funFactEn = funFactEn,
            funFactHi = funFactHi,
            tagsString = tags.joinToString(","),
            isBookmarked = isBookmarked,
            isCustomUserQuestion = isCustomUserQuestion,
            timestamp = timestamp
        )
    }
}
