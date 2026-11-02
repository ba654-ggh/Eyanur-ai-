package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.ui.viewmodel.MainUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizScreen(
    uiState: MainUiState,
    onSelectOption: (Int) -> Unit,
    onSubmitAnswer: () -> Unit,
    onNextQuestion: () -> Unit,
    onRestartQuiz: () -> Unit,
    modifier: Modifier = Modifier
) {
    val isHindi = uiState.isHindiMode
    val quiz = uiState.quizState

    Column(
        modifier = modifier
            .fillMaxSize()
            .testTag("quiz_screen")
    ) {
        TopAppBar(
            title = {
                Text(
                    text = if (isHindi) "ज्ञान परीक्षा (Quiz Challenge) 🏆" else "Quiz Challenge 🏆",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.surface
            )
        )

        if (quiz.isCompleted) {
            // Completion Screen
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = if (quiz.score >= 4) "🏆🎉" else "👏✨",
                    style = MaterialTheme.typography.displayLarge
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = if (isHindi) "क्विज संपन्न हुआ!" else "Quiz Completed!",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))

                Surface(
                    shape = RoundedCornerShape(20.dp),
                    color = MaterialTheme.colorScheme.primaryContainer,
                    modifier = Modifier.padding(vertical = 12.dp)
                ) {
                    Text(
                        text = if (isHindi) "आपका स्कोर: ${quiz.score} / ${quiz.questions.size}" else "Your Score: ${quiz.score} / ${quiz.questions.size}",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = if (quiz.score >= 4) {
                        if (isHindi) "शानदार! आपकी सामान्य ज्ञान और वैज्ञानिक समझ अद्भुत है।" else "Outstanding! Your knowledge and analytical skills are impressive."
                    } else {
                        if (isHindi) "अच्छा प्रयास! रोज नए सवाल पढ़कर आप और भी बेहतर बन सकते हैं।" else "Good effort! Read more questions daily to master every topic."
                    },
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(28.dp))

                Button(
                    onClick = onRestartQuiz,
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .height(50.dp)
                        .testTag("restart_quiz_btn")
                ) {
                    Icon(imageVector = Icons.Default.Refresh, contentDescription = "Restart")
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = if (isHindi) "पुनः अभ्यास करें" else "Play Again",
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        } else if (quiz.questions.isNotEmpty() && quiz.currentIndex < quiz.questions.size) {
            val currentQ = quiz.questions[quiz.currentIndex]
            val questionText = if (isHindi) currentQ.questionHi else currentQ.questionEn
            val options = if (isHindi) currentQ.optionsHi else currentQ.optionsEn
            val explanation = if (isHindi) currentQ.explanationHi else currentQ.explanationEn
            val progress = (quiz.currentIndex + 1).toFloat() / quiz.questions.size

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Progress Bar & Header
                item {
                    Column {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = if (isHindi) "प्रश्न ${quiz.currentIndex + 1} / ${quiz.questions.size}" else "Question ${quiz.currentIndex + 1} of ${quiz.questions.size}",
                                style = MaterialTheme.typography.labelLarge,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Text(
                                text = if (isHindi) "स्कोर: ${quiz.score}" else "Score: ${quiz.score}",
                                style = MaterialTheme.typography.labelLarge,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.secondary
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        LinearProgressIndicator(
                            progress = { progress },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(8.dp),
                            color = MaterialTheme.colorScheme.primary,
                            trackColor = MaterialTheme.colorScheme.surfaceVariant
                        )
                    }
                }

                // Question Card
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(18.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                    ) {
                        Text(
                            text = questionText,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.padding(20.dp)
                        )
                    }
                }

                // Options List
                items(options.size) { index ->
                    val optionText = options[index]
                    val isSelected = quiz.selectedOptionIndex == index
                    val isCorrect = index == currentQ.correctIndex

                    val borderColor = when {
                        !quiz.isSubmitted && isSelected -> MaterialTheme.colorScheme.primary
                        quiz.isSubmitted && isCorrect -> Color(0xFF16A34A)
                        quiz.isSubmitted && isSelected && !isCorrect -> MaterialTheme.colorScheme.error
                        else -> MaterialTheme.colorScheme.outlineVariant
                    }

                    val containerColor = when {
                        !quiz.isSubmitted && isSelected -> MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f)
                        quiz.isSubmitted && isCorrect -> Color(0xFFDCFCE7)
                        quiz.isSubmitted && isSelected && !isCorrect -> Color(0xFFFEE2E2)
                        else -> MaterialTheme.colorScheme.surface
                    }

                    Surface(
                        shape = RoundedCornerShape(14.dp),
                        color = containerColor,
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.5.dp, borderColor, RoundedCornerShape(14.dp))
                            .clickable(enabled = !quiz.isSubmitted) { onSelectOption(index) }
                            .testTag("quiz_option_$index")
                    ) {
                        Row(
                            modifier = Modifier.padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Surface(
                                shape = CircleShape,
                                color = if (isSelected || (quiz.isSubmitted && isCorrect)) borderColor else MaterialTheme.colorScheme.surfaceVariant,
                                modifier = Modifier.size(28.dp)
                            ) {
                                Box(contentAlignment = Alignment.Center) {
                                    if (quiz.isSubmitted) {
                                        if (isCorrect) {
                                            Icon(imageVector = Icons.Default.Check, contentDescription = "Correct", tint = Color.White, modifier = Modifier.size(16.dp))
                                        } else if (isSelected) {
                                            Icon(imageVector = Icons.Default.Close, contentDescription = "Wrong", tint = Color.White, modifier = Modifier.size(16.dp))
                                        } else {
                                            Text(text = ('A' + index).toString(), style = MaterialTheme.typography.labelSmall)
                                        }
                                    } else {
                                        Text(
                                            text = ('A' + index).toString(),
                                            style = MaterialTheme.typography.labelSmall,
                                            fontWeight = FontWeight.Bold,
                                            color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.width(12.dp))

                            Text(
                                text = optionText,
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = if (isSelected || (quiz.isSubmitted && isCorrect)) FontWeight.Bold else FontWeight.Normal,
                                color = MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }

                // Explanation when submitted
                if (quiz.isSubmitted) {
                    item {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(14.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.5f)
                            )
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text(
                                    text = if (isHindi) "💡 सही व्याख्या:" else "💡 Explanation:",
                                    style = MaterialTheme.typography.labelMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.secondary
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = explanation,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSecondaryContainer
                                )
                            }
                        }
                    }
                }

                // Action Button (Submit or Next)
                item {
                    Spacer(modifier = Modifier.height(8.dp))
                    if (!quiz.isSubmitted) {
                        Button(
                            onClick = onSubmitAnswer,
                            enabled = quiz.selectedOptionIndex != null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                                .testTag("quiz_submit_btn"),
                            shape = RoundedCornerShape(14.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                        ) {
                            Text(
                                text = if (isHindi) "उत्तर जांचें" else "Submit Answer",
                                fontWeight = FontWeight.Bold
                            )
                        }
                    } else {
                        Button(
                            onClick = onNextQuestion,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                                .testTag("quiz_next_btn"),
                            shape = RoundedCornerShape(14.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                        ) {
                            Text(
                                text = if (quiz.currentIndex + 1 < quiz.questions.size) {
                                    if (isHindi) "अगला सवाल →" else "Next Question →"
                                } else {
                                    if (isHindi) "स्कोर देखें 🏆" else "See Final Score 🏆"
                                },
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}
