package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.HelpOutline
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Badge
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ui.screens.AskScreen
import com.example.ui.screens.BookmarksScreen
import com.example.ui.screens.DetailScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.QuizScreen
import com.example.ui.screens.SettingsScreen
import com.example.ui.theme.MyApplicationTheme
import com.example.ui.viewmodel.QuestionViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                MainAppScreen()
            }
        }
    }
}

@Composable
fun MainAppScreen(
    viewModel: QuestionViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val isHindi = uiState.isHindiMode

    // If detail question is selected, show detail screen with full back navigation
    if (uiState.detailQuestion != null) {
        val detailQ = uiState.detailQuestion!!
        val isBookmarked = uiState.bookmarkedIds.contains(detailQ.id)

        DetailScreen(
            question = detailQ,
            isHindiDefault = isHindi,
            isBookmarked = isBookmarked,
            isTtsSpeaking = uiState.isTtsSpeaking,
            onBack = { viewModel.closeDetail() },
            onToggleBookmark = { viewModel.toggleBookmark(detailQ) },
            onSpeak = { text -> viewModel.speakText(text) },
            onStopSpeech = { viewModel.stopSpeech() }
        )
    } else {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            contentWindowInsets = WindowInsets.safeDrawing,
            bottomBar = {
                NavigationBar(
                    containerColor = MaterialTheme.colorScheme.surface,
                    tonalElevation = 6.dp,
                    modifier = Modifier.testTag("bottom_nav_bar")
                ) {
                    // Home Tab
                    NavigationBarItem(
                        selected = uiState.activeTab == "home",
                        onClick = { viewModel.setActiveTab("home") },
                        icon = {
                            Icon(
                                imageVector = Icons.Default.Home,
                                contentDescription = "Home",
                                modifier = Modifier.size(24.dp)
                            )
                        },
                        label = {
                            Text(
                                text = if (isHindi) "होम" else "Home",
                                style = MaterialTheme.typography.labelSmall
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.primary,
                            indicatorColor = MaterialTheme.colorScheme.primaryContainer
                        ),
                        modifier = Modifier.testTag("nav_item_home")
                    )

                    // Ask Tab
                    NavigationBarItem(
                        selected = uiState.activeTab == "ask",
                        onClick = { viewModel.setActiveTab("ask") },
                        icon = {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.HelpOutline,
                                contentDescription = "Ask",
                                modifier = Modifier.size(24.dp)
                            )
                        },
                        label = {
                            Text(
                                text = if (isHindi) "पूछें" else "Ask",
                                style = MaterialTheme.typography.labelSmall
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.primary,
                            indicatorColor = MaterialTheme.colorScheme.primaryContainer
                        ),
                        modifier = Modifier.testTag("nav_item_ask")
                    )

                    // Quiz Tab
                    NavigationBarItem(
                        selected = uiState.activeTab == "quiz",
                        onClick = { viewModel.setActiveTab("quiz") },
                        icon = {
                            Icon(
                                imageVector = Icons.Default.Quiz,
                                contentDescription = "Quiz",
                                modifier = Modifier.size(24.dp)
                            )
                        },
                        label = {
                            Text(
                                text = if (isHindi) "क्विज" else "Quiz",
                                style = MaterialTheme.typography.labelSmall
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.primary,
                            indicatorColor = MaterialTheme.colorScheme.primaryContainer
                        ),
                        modifier = Modifier.testTag("nav_item_quiz")
                    )

                    // Bookmarks Tab
                    NavigationBarItem(
                        selected = uiState.activeTab == "bookmarks",
                        onClick = { viewModel.setActiveTab("bookmarks") },
                        icon = {
                            if (uiState.bookmarkedIds.isNotEmpty()) {
                                BadgedBox(badge = { Badge { Text(uiState.bookmarkedIds.size.toString()) } }) {
                                    Icon(
                                        imageVector = Icons.Default.Bookmark,
                                        contentDescription = "Bookmarks",
                                        modifier = Modifier.size(24.dp)
                                    )
                                }
                            } else {
                                Icon(
                                    imageVector = Icons.Default.Bookmark,
                                    contentDescription = "Bookmarks",
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                        },
                        label = {
                            Text(
                                text = if (isHindi) "सहेजे" else "Saved",
                                style = MaterialTheme.typography.labelSmall
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.primary,
                            indicatorColor = MaterialTheme.colorScheme.primaryContainer
                        ),
                        modifier = Modifier.testTag("nav_item_bookmarks")
                    )

                    // Settings Tab
                    NavigationBarItem(
                        selected = uiState.activeTab == "settings",
                        onClick = { viewModel.setActiveTab("settings") },
                        icon = {
                            Icon(
                                imageVector = Icons.Default.Settings,
                                contentDescription = "Settings",
                                modifier = Modifier.size(24.dp)
                            )
                        },
                        label = {
                            Text(
                                text = if (isHindi) "सेटिंग्स" else "Settings",
                                style = MaterialTheme.typography.labelSmall
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.primary,
                            indicatorColor = MaterialTheme.colorScheme.primaryContainer
                        ),
                        modifier = Modifier.testTag("nav_item_settings")
                    )
                }
            }
        ) { innerPadding ->
            AnimatedContent(
                targetState = uiState.activeTab,
                transitionSpec = { fadeIn() togetherWith fadeOut() },
                label = "tab_transition",
                modifier = Modifier.padding(innerPadding)
            ) { tab ->
                when (tab) {
                    "home" -> HomeScreen(
                        uiState = uiState,
                        onSearchChange = { query -> viewModel.onSearchQueryChange(query) },
                        onCategorySelect = { cat -> viewModel.selectCategory(cat) },
                        onQuestionClick = { q -> viewModel.openDetail(q) },
                        onToggleBookmark = { q -> viewModel.toggleBookmark(q) },
                        onSpeak = { text -> viewModel.speakText(text) },
                        onToggleLanguage = { viewModel.toggleLanguage() },
                        onNavigateToAsk = { viewModel.setActiveTab("ask") }
                    )
                    "ask" -> AskScreen(
                        uiState = uiState,
                        onInputChange = { text -> viewModel.onAskInputChange(text) },
                        onSubmitAsk = { prompt -> viewModel.submitAskQuestion(prompt) },
                        onQuestionClick = { q -> viewModel.openDetail(q) },
                        onToggleBookmark = { q -> viewModel.toggleBookmark(q) },
                        onSpeak = { text -> viewModel.speakText(text) }
                    )
                    "quiz" -> QuizScreen(
                        uiState = uiState,
                        onSelectOption = { idx -> viewModel.selectQuizOption(idx) },
                        onSubmitAnswer = { viewModel.submitQuizAnswer() },
                        onNextQuestion = { viewModel.nextQuizQuestion() },
                        onRestartQuiz = { viewModel.restartQuiz() }
                    )
                    "bookmarks" -> BookmarksScreen(
                        uiState = uiState,
                        onQuestionClick = { q -> viewModel.openDetail(q) },
                        onToggleBookmark = { q -> viewModel.toggleBookmark(q) },
                        onSpeak = { text -> viewModel.speakText(text) },
                        onExploreClick = { viewModel.setActiveTab("home") }
                    )
                    "settings" -> SettingsScreen(
                        uiState = uiState,
                        onToggleLanguage = { viewModel.toggleLanguage() },
                        onTestAudio = {
                            viewModel.speakText(
                                if (isHindi) "यह ऑल क्यू एंड ए ऐप की आवाज है।" else "This is the voice of All Q&A app."
                            )
                        }
                    )
                }
            }
        }
    }
}
