package com.example.finaltask2021.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.finaltask2021.data.database.COLUMN_NAME_ID
import com.example.finaltask2021.data.database.COLUMN_NAME_WORD
import com.example.finaltask2021.data.database.TABLE_NAME
import com.example.finaltask2021.data.remote.dto.word_detailed.Result
import com.example.finaltask2021.data.remote.dto.word_detailed.WordDto

@Entity(tableName = TABLE_NAME)
data class Word(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = COLUMN_NAME_ID) var id: Int = 0,
    @Ignore val results: List<Result>?,
    @ColumnInfo(name = COLUMN_NAME_WORD) val word: String,
) {
    constructor(word: String) : this(0, null, word)
}

fun WordDto.toWord() = Word(
    results = results,
    word = word
)