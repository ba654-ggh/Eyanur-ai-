package com.example.data.model

import androidx.compose.ui.graphics.Color
import com.example.ui.theme.CatCuriosity
import com.example.ui.theme.CatExam
import com.example.ui.theme.CatGk
import com.example.ui.theme.CatHealth
import com.example.ui.theme.CatHistory
import com.example.ui.theme.CatMath
import com.example.ui.theme.CatScience
import com.example.ui.theme.CatTech

enum class QuestionCategory(
    val titleEn: String,
    val titleHi: String,
    val iconEmoji: String,
    val badgeColor: Color
) {
    ALL("All", "सभी", "🌟", Color(0xFF2563EB)),
    SCIENCE("Science", "विज्ञान", "🔬", CatScience),
    GK("General Knowledge", "सामान्य ज्ञान", "🌍", CatGk),
    MATHEMATICS("Maths & Logic", "गणित एवं तर्क", "🔢", CatMath),
    HISTORY("History", "इतिहास", "📜", CatHistory),
    TECHNOLOGY("Technology & AI", "तकनीक एवं एआई", "💻", CatTech),
    CURIOSITY("Why & How", "क्यों और कैसे", "💡", CatCuriosity),
    EXAM_PREP("Exam Preparation", "परीक्षा तैयारी", "📚", CatExam),
    HEALTH("Health & Biology", "स्वास्थ्य एवं शरीर", "🌿", CatHealth)
}
