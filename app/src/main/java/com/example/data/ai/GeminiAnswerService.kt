package com.example.data.ai

import com.example.BuildConfig
import com.example.data.knowledge.KnowledgeBase
import com.example.data.model.QuestionCategory
import com.example.data.model.QuestionItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject
import java.util.UUID
import java.util.concurrent.TimeUnit

class GeminiAnswerService {

    private val client = OkHttpClient.Builder()
        .connectTimeout(15, TimeUnit.SECONDS)
        .readTimeout(25, TimeUnit.SECONDS)
        .build()

    suspend fun answerQuestion(
        userPrompt: String,
        preferredLanguage: String = "both" // "en", "hi", "both"
    ): QuestionItem = withContext(Dispatchers.IO) {
        val trimmed = userPrompt.trim()

        // 1. First, check if there's a strong match in the offline KnowledgeBase
        val exactMatch = KnowledgeBase.allQuestions.firstOrNull {
            it.questionEn.equals(trimmed, ignoreCase = true) ||
                    it.questionHi.equals(trimmed, ignoreCase = true)
        }
        if (exactMatch != null) {
            return@withContext exactMatch
        }

        // 2. Try Gemini API if valid key is available
        val apiKey = try {
            BuildConfig.GEMINI_API_KEY
        } catch (_: Exception) {
            ""
        }

        if (apiKey.isNotBlank() && apiKey != "MY_GEMINI_API_KEY") {
            try {
                val aiResult = callGeminiApi(trimmed, apiKey)
                if (aiResult != null) {
                    return@withContext aiResult
                }
            } catch (e: Exception) {
                // Fall back gracefully to local engine
                e.printStackTrace()
            }
        }

        // 3. Fallback: Intelligent Knowledge Synthesis Engine
        return@withContext generateSynthesizedAnswer(trimmed)
    }

    private fun callGeminiApi(prompt: String, apiKey: String): QuestionItem? {
        val systemInstruction = """
            You are an expert, friendly educational AI tutor in an app called 'All Q&A'.
            The user is asking a question: "$prompt"
            Provide a complete, accurate, and easy-to-understand response in JSON format.
            Return ONLY a valid JSON object matching this schema:
            {
              "questionEn": "Standardized question in English",
              "questionHi": "Question in Hindi (Devanagari)",
              "answerEn": "Clear, detailed, step-by-step answer in English (2-4 paragraphs).",
              "answerHi": "Clear, detailed, step-by-step answer in Hindi (2-4 paragraphs).",
              "category": "SCIENCE or GK or MATHEMATICS or HISTORY or TECHNOLOGY or CURIOSITY or EXAM_PREP or HEALTH",
              "difficulty": "Easy or Medium or Hard",
              "keyTakeawayEn": "Single sentence takeaway in English",
              "keyTakeawayHi": "Single sentence takeaway in Hindi",
              "funFactEn": "Fascinating related trivia or fact in English",
              "funFactHi": "Fascinating related trivia or fact in Hindi",
              "tags": ["tag1", "tag2", "tag3"]
            }
        """.trimIndent()

        val url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key=$apiKey"

        val requestJson = JSONObject().apply {
            val contents = JSONArray().apply {
                put(JSONObject().apply {
                    put("role", "user")
                    put("parts", JSONArray().apply {
                        put(JSONObject().put("text", systemInstruction))
                    })
                })
            }
            put("contents", contents)
            put("generationConfig", JSONObject().apply {
                put("responseMimeType", "application/json")
                put("temperature", 0.3)
            })
        }

        val request = Request.Builder()
            .url(url)
            .post(requestJson.toString().toRequestBody("application/json".toMediaType()))
            .build()

        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) return null
            val responseBody = response.body?.string() ?: return null

            val root = JSONObject(responseBody)
            val candidates = root.optJSONArray("candidates") ?: return null
            if (candidates.length() == 0) return null
            val content = candidates.getJSONObject(0).optJSONObject("content") ?: return null
            val parts = content.optJSONArray("parts") ?: return null
            if (parts.length() == 0) return null
            val rawText = parts.getJSONObject(0).optString("text")

            val json = JSONObject(rawText)
            val catStr = json.optString("category", "CURIOSITY").uppercase()
            val category = try {
                QuestionCategory.valueOf(catStr)
            } catch (_: Exception) {
                QuestionCategory.CURIOSITY
            }

            val tagsList = mutableListOf<String>()
            val tagsArr = json.optJSONArray("tags")
            if (tagsArr != null) {
                for (i in 0 until tagsArr.length()) {
                    tagsList.add(tagsArr.getString(i))
                }
            }

            return QuestionItem(
                id = "ai_" + UUID.randomUUID().toString().take(8),
                questionEn = json.optString("questionEn", prompt),
                questionHi = json.optString("questionHi", prompt),
                answerEn = json.optString("answerEn", "Answer generated successfully."),
                answerHi = json.optString("answerHi", "उत्तर सफलतापूर्वक तैयार किया गया।"),
                category = category,
                difficulty = json.optString("difficulty", "Medium"),
                keyTakeawayEn = json.optString("keyTakeawayEn", ""),
                keyTakeawayHi = json.optString("keyTakeawayHi", ""),
                funFactEn = json.optString("funFactEn", ""),
                funFactHi = json.optString("funFactHi", ""),
                tags = tagsList,
                isCustomUserQuestion = true
            )
        }
    }

    private fun generateSynthesizedAnswer(query: String): QuestionItem {
        val lower = query.lowercase()

        // Match against existing knowledge keywords
        val closestMatch = KnowledgeBase.allQuestions.maxByOrNull { item ->
            var score = 0
            val words = lower.split(" ", "?", ".", ",", "!", "-").filter { it.length > 2 }
            for (word in words) {
                if (item.questionEn.lowercase().contains(word)) score += 3
                if (item.questionHi.lowercase().contains(word)) score += 3
                if (item.tags.any { it.lowercase().contains(word) }) score += 2
                if (item.answerEn.lowercase().contains(word)) score += 1
            }
            score
        }

        if (closestMatch != null) {
            val words = lower.split(" ", "?", ".", ",", "!", "-").filter { it.length > 2 }
            val matchedWords = words.count {
                closestMatch.questionEn.lowercase().contains(it) ||
                        closestMatch.questionHi.lowercase().contains(it) ||
                        closestMatch.tags.any { t -> t.lowercase().contains(it) }
            }
            if (matchedWords >= 1) {
                return closestMatch
            }
        }

        // Detect Category and produce structured educational guidance
        val category = when {
            lower.contains("how") || lower.contains("why") || lower.contains("kyu") || lower.contains("kaise") -> QuestionCategory.CURIOSITY
            lower.contains("solve") || lower.contains("calculate") || lower.contains("math") || lower.contains("+") || lower.contains("percent") -> QuestionCategory.MATHEMATICS
            lower.contains("who") || lower.contains("capital") || lower.contains("country") || lower.contains("world") || lower.contains("largest") -> QuestionCategory.GK
            lower.contains("phone") || lower.contains("computer") || lower.contains("ai") || lower.contains("internet") || lower.contains("code") -> QuestionCategory.TECHNOLOGY
            lower.contains("body") || lower.contains("food") || lower.contains("sleep") || lower.contains("health") || lower.contains("blood") -> QuestionCategory.HEALTH
            lower.contains("war") || lower.contains("king") || lower.contains("history") || lower.contains("ancient") -> QuestionCategory.HISTORY
            lower.contains("exam") || lower.contains("upsc") || lower.contains("ssc") || lower.contains("constitution") -> QuestionCategory.EXAM_PREP
            else -> QuestionCategory.SCIENCE
        }

        return QuestionItem(
            id = "synth_" + UUID.randomUUID().toString().take(8),
            questionEn = query,
            questionHi = query,
            answerEn = "Here is an instant breakdown for your question: \"$query\"\n\n" +
                    "• Core Insight: Questions like this touch upon fundamental principles of ${category.titleEn.lowercase()}. When analyzing this topic, experts consider observational facts, scientific principles, and established verified references.\n\n" +
                    "• Detailed Explanation: To explore this further, explore related questions in our ${category.titleEn} category. You can also connect your Gemini API Key in the AI Studio Secrets panel to enable real-time generative AI answers for any custom question!",
            answerHi = "आपके प्रश्न \"$query\" का विस्तृत समाधान:\n\n" +
                    "• मुख्य बिंदु: यह प्रश्न ${category.titleHi} के महत्वपूर्ण सिद्धांतों से संबंधित है। इस विषय को समझने के लिए प्रामाणिक तथ्यों और वैज्ञानिक नियमों का अध्ययन आवश्यक है।\n\n" +
                    "• संपूर्ण विश्लेषण: इस विषय पर और अधिक जानकारी के लिए ऐप की '${category.titleHi}' श्रेणी देखें। आप AI Studio Secrets पैनल में Gemini API Key जोड़कर किसी भी सवाल का रीयल-टाइम AI उत्तर भी प्राप्त कर सकते हैं!",
            category = category,
            difficulty = "Medium",
            keyTakeawayEn = "Explore related questions in ${category.titleEn} for full step-by-step mastery.",
            keyTakeawayHi = "विस्तृत समझ के लिए ${category.titleHi} श्रेणी के संबंधित प्रश्न देखें।",
            funFactEn = "Continuous curiosity and asking questions is the fastest way human beings expand knowledge!",
            funFactHi = "लगातार सवाल पूछना और जिज्ञासा बनाए रखना ही ज्ञानवर्धन का सबसे शक्तिशाली तरीका है!",
            tags = listOf("answer", "knowledge", category.name.lowercase()),
            isCustomUserQuestion = true
        )
    }
}
