package com.example.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.data.model.QuestionItem
import com.example.ui.components.QuestionCard
import com.example.ui.viewmodel.MainUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookmarksScreen(
    uiState: MainUiState,
    onQuestionClick: (QuestionItem) -> Unit,
    onToggleBookmark: (QuestionItem) -> Unit,
    onSpeak: (String) -> Unit,
    onExploreClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val isHindi = uiState.isHindiMode
    val bookmarks = uiState.bookmarkedList

    Column(
        modifier = modifier
            .fillMaxSize()
            .testTag("bookmarks_screen")
    ) {
        TopAppBar(
            title = {
                Text(
                    text = if (isHindi) "सहेजे गए सवाल (${bookmarks.size}) 🔖" else "Saved Questions (${bookmarks.size}) 🔖",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.surface
            )
        )

        if (bookmarks.isEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "🔖",
                    style = MaterialTheme.typography.displayLarge
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = if (isHindi) "कोई सवाल सहेजा नहीं गया" else "No saved questions yet",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = if (isHindi) "किसी भी सवाल के कार्ड पर बुकमार्क (🔖) आइकन दबाकर उसे बाद में पढ़ने के लिए यहाँ सहेजें।" else "Tap the bookmark (🔖) icon on any question to save it for quick offline revision.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = onExploreClick,
                    shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                    modifier = Modifier.testTag("explore_questions_btn")
                ) {
                    Text(text = if (isHindi) "सवाल देखें" else "Explore Questions")
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                items(bookmarks, key = { it.id }) { question ->
                    val isBookmarked = true
                    val speakContent = if (isHindi) "${question.questionHi}. ${question.answerHi}" else "${question.questionEn}. ${question.answerEn}"

                    QuestionCard(
                        question = question,
                        isHindi = isHindi,
                        isBookmarked = isBookmarked,
                        onCardClick = { onQuestionClick(question) },
                        onToggleBookmark = { onToggleBookmark(question) },
                        onSpeak = { onSpeak(speakContent) }
                    )
                }
            }
        }
    }
}
