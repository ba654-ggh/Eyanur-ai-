package com.example.ui.viewmodel

import android.app.Application
import android.speech.tts.TextToSpeech
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.AppDatabase
import com.example.data.model.QuestionCategory
import com.example.data.model.QuestionItem
import com.example.data.repository.QuestionRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Locale

data class QuizQuestion(
    val questionEn: String,
    val questionHi: String,
    val optionsEn: List<String>,
    val optionsHi: List<String>,
    val correctIndex: Int,
    val explanationEn: String,
    val explanationHi: String
)

data class QuizState(
    val questions: List<QuizQuestion> = emptyList(),
    val currentIndex: Int = 0,
    val selectedOptionIndex: Int? = null,
    val isSubmitted: Boolean = false,
    val score: Int = 0,
    val isCompleted: Boolean = false
)

data class MainUiState(
    val searchQuery: String = "",
    val selectedCategory: QuestionCategory = QuestionCategory.ALL,
    val isHindiMode: Boolean = true, // Default to Hindi as per user's prompt language
    val questions: List<QuestionItem> = emptyList(),
    val questionOfDay: QuestionItem? = null,
    val detailQuestion: QuestionItem? = null,
    val askInput: String = "",
    val isAskingLoading: Boolean = false,
    val askResult: QuestionItem? = null,
    val bookmarkedIds: Set<String> = emptySet(),
    val bookmarkedList: List<QuestionItem> = emptyList(),
    val activeTab: String = "home", // "home", "ask", "quiz", "bookmarks", "settings"
    val quizState: QuizState = QuizState(),
    val isTtsSpeaking: Boolean = false
)

class QuestionViewModel(application: Application) : AndroidViewModel(application), TextToSpeech.OnInitListener {

    private val repository: QuestionRepository
    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    private var tts: TextToSpeech? = null
    private var isTtsReady = false

    init {
        val db = AppDatabase.getInstance(application)
        repository = QuestionRepository(db.questionDao())

        tts = TextToSpeech(application, this)

        val qotd = repository.getQuestionOfDay()
        val initialQuestions = repository.getAllQuestions()

        _uiState.update {
            it.copy(
                questions = initialQuestions,
                questionOfDay = qotd
            )
        }

        initQuiz()

        // Observe bookmarks
        viewModelScope.launch {
            repository.bookmarkedQuestions.collect { bookmarks ->
                val ids = bookmarks.map { it.id }.toSet()
                _uiState.update {
                    it.copy(
                        bookmarkedIds = ids,
                        bookmarkedList = bookmarks
                    )
                }
            }
        }
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            isTtsReady = true
            tts?.language = Locale.forLanguageTag("hi-IN")
        }
    }

    fun setLanguageHindi(isHindi: Boolean) {
        _uiState.update { it.copy(isHindiMode = isHindi) }
        if (isTtsReady) {
            tts?.language = if (isHindi) Locale.forLanguageTag("hi-IN") else Locale.US
        }
    }

    fun toggleLanguage() {
        setLanguageHindi(!_uiState.value.isHindiMode)
    }

    fun setActiveTab(tab: String) {
        stopSpeech()
        _uiState.update { it.copy(activeTab = tab) }
    }

    fun onSearchQueryChange(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
        refreshQuestionList()
    }

    fun selectCategory(category: QuestionCategory) {
        _uiState.update { it.copy(selectedCategory = category) }
        refreshQuestionList()
    }

    private fun refreshQuestionList() {
        val query = _uiState.value.searchQuery
        val cat = _uiState.value.selectedCategory
        val list = repository.getAllQuestions(cat, query)
        _uiState.update { it.copy(questions = list) }
    }

    fun openDetail(question: QuestionItem) {
        stopSpeech()
        _uiState.update { it.copy(detailQuestion = question) }
    }

    fun closeDetail() {
        stopSpeech()
        _uiState.update { it.copy(detailQuestion = null) }
    }

    fun onAskInputChange(text: String) {
        _uiState.update { it.copy(askInput = text) }
    }

    fun submitAskQuestion(prompt: String = _uiState.value.askInput) {
        if (prompt.isBlank()) return
        stopSpeech()
        _uiState.update { it.copy(isAskingLoading = true, askResult = null) }

        viewModelScope.launch {
            try {
                val result = repository.askQuestion(prompt)
                _uiState.update {
                    it.copy(
                        isAskingLoading = false,
                        askResult = result,
                        detailQuestion = result
                    )
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(isAskingLoading = false) }
            }
        }
    }

    fun toggleBookmark(item: QuestionItem) {
        viewModelScope.launch {
            repository.toggleBookmark(item)
            val isNowBookmarked = repository.isBookmarked(item.id)
            val updatedSet = _uiState.value.bookmarkedIds.toMutableSet()
            if (isNowBookmarked) updatedSet.add(item.id) else updatedSet.remove(item.id)

            // Update in detailQuestion if active
            val currentDetail = _uiState.value.detailQuestion
            val updatedDetail = if (currentDetail?.id == item.id) {
                currentDetail.copy(isBookmarked = isNowBookmarked)
            } else currentDetail

            _uiState.update {
                it.copy(
                    bookmarkedIds = updatedSet,
                    detailQuestion = updatedDetail
                )
            }
        }
    }

    fun speakText(text: String) {
        if (!isTtsReady || tts == null) return
        if (_uiState.value.isTtsSpeaking) {
            stopSpeech()
            return
        }
        val loc = if (_uiState.value.isHindiMode) Locale.forLanguageTag("hi-IN") else Locale.US
        tts?.language = loc
        tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, "QA_TTS_ID")
        _uiState.update { it.copy(isTtsSpeaking = true) }
    }

    fun stopSpeech() {
        tts?.stop()
        _uiState.update { it.copy(isTtsSpeaking = false) }
    }

    // ================= QUIZ LOGIC =================
    private fun initQuiz() {
        val sampleQuiz = listOf(
            QuizQuestion(
                questionEn = "Why does the sky appear blue to us?",
                questionHi = "दिन के समय आकाश नीला क्यों दिखाई देता है?",
                optionsEn = listOf("Ocean reflection", "Rayleigh scattering", "Ozone color", "Clouds moisture"),
                optionsHi = listOf("समुद्र का परावर्तन", "रेले प्रकीर्णन", "ओजोन का रंग", "बादलों की नमी"),
                correctIndex = 1,
                explanationEn = "Blue light has a short wavelength and scatters most in atmosphere.",
                explanationHi = "नीले प्रकाश की छोटी तरंगदैर्ध्य वायुमंडल में सबसे ज्यादा बिखरती है।"
            ),
            QuizQuestion(
                questionEn = "Which is the longest river on Earth?",
                questionHi = "विश्व की सबसे लम्बी नदी कौन सी है?",
                optionsEn = listOf("Amazon River", "Nile River", "Yangtze River", "Mississippi River"),
                optionsHi = listOf("अमेज़न नदी", "नील नदी", "यांग्त्से नदी", "मिसिसिपी नदी"),
                correctIndex = 1,
                explanationEn = "The Nile in Africa is ~6,650 km long.",
                explanationHi = "नील नदी लगभग 6,650 किमी लम्बी है।"
            ),
            QuizQuestion(
                questionEn = "Who is known as the Father of the Indian Constitution?",
                questionHi = "भारतीय संविधान का जनक किसे कहा जाता है?",
                optionsEn = listOf("Mahatma Gandhi", "Dr. B. R. Ambedkar", "Jawaharlal Nehru", "Sardar Patel"),
                optionsHi = listOf("महात्मा गांधी", "डॉ. बी. आर. आंबेडकर", "जवाहरलाल नेहरू", "सरदार पटेल"),
                correctIndex = 1,
                explanationEn = "Dr. Ambedkar was the Chairman of the Drafting Committee.",
                explanationHi = "डॉ. आंबेडकर संविधान की प्रारूप समिति के अध्यक्ष थे।"
            ),
            QuizQuestion(
                questionEn = "What is the approximate speed of light in vacuum?",
                questionHi = "निर्वात में प्रकाश की गति लगभग कितनी होती है?",
                optionsEn = listOf("30,000 km/s", "150,000 km/s", "300,000 km/s", "1,000,000 km/s"),
                optionsHi = listOf("30,000 किमी/से", "1,50,000 किमी/से", "3,00,000 किमी/से", "10,00,000 किमी/से"),
                correctIndex = 2,
                explanationEn = "Exact value: 299,792,458 m/s (~300,000 km/s).",
                explanationHi = "प्रकाश की गति लगभग 3 लाख किमी प्रति सेकंड होती है।"
            ),
            QuizQuestion(
                questionEn = "Why do birds fly in a V-formation?",
                questionHi = "पक्षी आसमान में 'V' आकार में क्यों उड़ते हैं?",
                optionsEn = listOf("To save flight energy", "To look scary", "To fly faster than wind", "To find food"),
                optionsHi = listOf("उड़ान ऊर्जा बचाने के लिए", "शिकारियों को डराने", "हवा से तेज उड़ने", "भोजन खोजने"),
                correctIndex = 0,
                explanationEn = "V-formation catches aerodynamic upwash, saving up to 30% energy.",
                explanationHi = "V-आकार से पक्षियों को हवा का सहारा मिलता है जिससे 30% ऊर्जा बचती है।"
            )
        )

        _uiState.update {
            it.copy(
                quizState = QuizState(
                    questions = sampleQuiz,
                    currentIndex = 0,
                    selectedOptionIndex = null,
                    isSubmitted = false,
                    score = 0,
                    isCompleted = false
                )
            )
        }
    }

    fun selectQuizOption(index: Int) {
        val current = _uiState.value.quizState
        if (current.isSubmitted || current.isCompleted) return
        _uiState.update {
            it.copy(quizState = current.copy(selectedOptionIndex = index))
        }
    }

    fun submitQuizAnswer() {
        val current = _uiState.value.quizState
        val selected = current.selectedOptionIndex ?: return
        if (current.isSubmitted) return

        val q = current.questions[current.currentIndex]
        val isCorrect = selected == q.correctIndex
        val newScore = if (isCorrect) current.score + 1 else current.score

        _uiState.update {
            it.copy(
                quizState = current.copy(
                    isSubmitted = true,
                    score = newScore
                )
            )
        }
    }

    fun nextQuizQuestion() {
        val current = _uiState.value.quizState
        if (current.currentIndex + 1 < current.questions.size) {
            _uiState.update {
                it.copy(
                    quizState = current.copy(
                        currentIndex = current.currentIndex + 1,
                        selectedOptionIndex = null,
                        isSubmitted = false
                    )
                )
            }
        } else {
            _uiState.update {
                it.copy(quizState = current.copy(isCompleted = true))
            }
        }
    }

    fun restartQuiz() {
        initQuiz()
    }

    override fun onCleared() {
        super.onCleared()
        tts?.stop()
        tts?.shutdown()
    }
}
