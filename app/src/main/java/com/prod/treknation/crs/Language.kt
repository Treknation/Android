package com.prod.treknation.crs

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Language(
        @PrimaryKey var TYPE: String,
        @ColumnInfo(name = "userID") var UserID: String? = null,
        @ColumnInfo(name = "LanguageName") var LanguageName: String? = null,
        @ColumnInfo(name = "reading") var reading: String? = null,
        @ColumnInfo(name = "writing") var writing: String? = null,
        @ColumnInfo(name = "speaking") var speaking: String? = null,
        @ColumnInfo(name = "listening") var listening: String? = null,
        @ColumnInfo(name = "readingCLB") var readingCLB: Int = 0,
        @ColumnInfo(name = "writingCLB") var writingCLB: Int = 0,
        @ColumnInfo(name = "speakingCLB") var speakingCLB: Int = 0,
        @ColumnInfo(name = "listeningCLB") var listeningCLB: Int = 0,
)