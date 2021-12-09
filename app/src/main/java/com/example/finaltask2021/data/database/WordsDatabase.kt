package com.example.finaltask2021.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.finaltask2021.domain.model.Word
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Word::class], version = DATABASE_VERSION, exportSchema = false)
abstract class WordsDatabase : RoomDatabase() {

    abstract fun quizDao(): WordsDao

    companion object {

        @Volatile
        private var INSTANCE: WordsDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): WordsDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WordsDatabase::class.java,
                    DATABASE_NAME
                )
                    .addCallback(QuizDatabaseCallback(scope))
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }

        private class QuizDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {

            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                // If you want to keep the data through app restarts,
                // comment out the following line.
                INSTANCE?.let { database ->
                    scope.launch {
                        populateDatabase(database.quizDao())
                    }
                }
            }

            suspend fun populateDatabase(wordsDao: WordsDao) {
                getSampleList().forEach {
                    wordsDao.insert(it)
                }
            }
        }

        fun getSampleList() = listOf<Word>(
            Word(
                word = "example",
                definition = "something",
                results = null
            ),
            Word(
                word = "semen",
                definition = "lol. idiot",
                results = null
            )
        )
    }
}