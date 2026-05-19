package com.example.andproject.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore by preferencesDataStore(name = "user_preferences")

@Singleton
class PreferencesManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val USER_NAME = stringPreferencesKey("user_name")
    private val USER_CLASS = stringPreferencesKey("user_class")
    private val DARK_MODE = booleanPreferencesKey("dark_mode")
    private val TOTAL_XP = intPreferencesKey("total_xp")

    val userName: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[USER_NAME] ?: "Arathorn"
    }

    val userClass: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[USER_CLASS] ?: "wizard"
    }

    val totalXp: Flow<Int> = context.dataStore.data.map { preferences ->
        preferences[TOTAL_XP] ?: 0
    }

    suspend fun updateUserName(name: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_NAME] = name
        }
    }

    suspend fun updateUserClass(classId: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_CLASS] = classId
        }
    }

    suspend fun addXp(amount: Int) {
        context.dataStore.edit { preferences ->
            val current = preferences[TOTAL_XP] ?: 0
            preferences[TOTAL_XP] = current + amount
        }
    }
    
    suspend fun resetProgress() {
        context.dataStore.edit { preferences ->
            preferences[TOTAL_XP] = 0
            preferences[USER_NAME] = "Arathorn"
            preferences[USER_CLASS] = "wizard"
        }
    }
}
