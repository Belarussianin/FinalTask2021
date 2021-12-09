package com.example.finaltask2021.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.finaltask2021.domain.model.Word
import kotlinx.coroutines.flow.Flow
import kotlin.random.Random

@Dao
interface WordsDao {

    @Query("SELECT * FROM $TABLE_NAME")
    fun getAll(): Flow<List<Word>>

    //@Query("SELECT * FROM $TABLE_NAME WHERE $COLUMN_NAME_ID = :id")
    //suspend fun getById(id: Int): Word?

    @Query("SELECT * FROM $TABLE_NAME WHERE $COLUMN_NAME_WORD = :word")
    suspend fun get(word: String): Word?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(word: Word)

    @Query("DELETE FROM $TABLE_NAME WHERE $COLUMN_NAME_WORD = :word")
    suspend fun delete(word: String)

    @Delete
    suspend fun delete(word: Word)

    @Query("SELECT * FROM $TABLE_NAME WHERE $COLUMN_NAME_WORD LIKE :search OR $COLUMN_NAME_DEFINITION LIKE :search")
    fun findWord(search: String): List<Word>
}