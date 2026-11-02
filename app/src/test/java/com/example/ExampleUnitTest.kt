package com.example

import com.example.data.knowledge.KnowledgeBase
import com.example.data.model.QuestionCategory
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class ExampleUnitTest {
    @Test
    fun knowledgeBase_isPopulated() {
        val all = KnowledgeBase.allQuestions
        assertTrue("Knowledge base should have items", all.size >= 10)
    }

    @Test
    fun knowledgeBase_searchWorks_inHindiAndEnglish() {
        val skySearch = KnowledgeBase.search("sky")
        assertTrue("Searching 'sky' should return sky question", skySearch.any { it.id == "sci_1" })

        val aakashSearch = KnowledgeBase.search("आकाश")
        assertTrue("Searching 'आकाश' should return sky question", aakashSearch.any { it.id == "sci_1" })
    }

    @Test
    fun knowledgeBase_categoryFilteringWorks() {
        val scienceQuestions = KnowledgeBase.search("", QuestionCategory.SCIENCE)
        assertTrue(scienceQuestions.isNotEmpty())
        assertTrue(scienceQuestions.all { it.category == QuestionCategory.SCIENCE })
    }

    @Test
    fun knowledgeBase_qotdReturnsNonNull() {
        val qotd = KnowledgeBase.getQuestionOfDay()
        assertNotNull(qotd)
        assertTrue(qotd.questionEn.isNotBlank())
        assertTrue(qotd.questionHi.isNotBlank())
    }
}
