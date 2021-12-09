package com.example.finaltask2021

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AppSettings(private val context: Context) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "userSettings")
    private val USER_DARK_THEME_KEY = booleanPreferencesKey(name = "user_dark_theme")

    //get the saved theme setting
    val getIsDarkTheme: Flow<Boolean?> = context.dataStore.data.map { preferences ->
        preferences[USER_DARK_THEME_KEY]
    }

    //save theme setting into datastore
    suspend fun saveIsDarkTheme(isDarkTheme: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[USER_DARK_THEME_KEY] = isDarkTheme
        }
    }
}

//class StoreUserEmail(private val context: Context) {
//
//    // to make sure there's only one instance
//    companion object {
//        private val Context.dataStoree: DataStore<Preferences> by preferencesDataStore("userEmail")
//        val USER_EMAIL_KEY = stringPreferencesKey("user_email")
//    }
//
//    //get the saved email
//    val getEmail: Flow<String?> = context.dataStoree.data
//        .map { preferences ->
//            preferences[USER_EMAIL_KEY] ?: "FirstLast@gmail.com"
//        }
//
//    //save email into datastore
//    suspend fun saveEmail(name: String) {
//        context.dataStoree.edit { preferences ->
//            preferences[USER_EMAIL_KEY] = name
//        }
//    }
//
//
//}