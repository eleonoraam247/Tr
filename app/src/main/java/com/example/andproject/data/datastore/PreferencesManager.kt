package com.example.andproject.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.Calendar
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore by preferencesDataStore(name = "user_preferences")

@Singleton
class PreferencesManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val USER_NAME       = stringPreferencesKey("user_name")
    private val USER_CLASS      = stringPreferencesKey("user_class")
    private val DARK_MODE       = booleanPreferencesKey("dark_mode")
    private val TOTAL_XP        = intPreferencesKey("total_xp")
    private val XP_TODAY        = intPreferencesKey("xp_today")
    private val STREAK          = intPreferencesKey("streak")
    private val LAST_ACTIVE_DAY = longPreferencesKey("last_active_day")  // epoch day
    private val TASKS_DONE_TOTAL = intPreferencesKey("tasks_done_total")

    val userName: Flow<String> = context.dataStore.data.map { it[USER_NAME] ?: "Arathorn" }
    val userClass: Flow<String> = context.dataStore.data.map { it[USER_CLASS] ?: "wizard" }
    val totalXp: Flow<Int> = context.dataStore.data.map { it[TOTAL_XP] ?: 0 }
    val xpToday: Flow<Int> = context.dataStore.data.map { it[XP_TODAY] ?: 0 }
    val streak: Flow<Int> = context.dataStore.data.map { it[STREAK] ?: 0 }
    val tasksDoneTotal: Flow<Int> = context.dataStore.data.map { it[TASKS_DONE_TOTAL] ?: 0 }

    suspend fun updateUserName(name: String) {
        context.dataStore.edit { it[USER_NAME] = name }
    }

    suspend fun updateUserClass(classId: String) {
        context.dataStore.edit { it[USER_CLASS] = classId }
    }

    // Вызывается при отметке задачи выполненной / невыполненной
    // amount > 0 = выполнено, amount < 0 = снято
    suspend fun addXp(amount: Int) {
        val todayEpochDay = todayEpochDay()
        context.dataStore.edit { prefs ->
            // --- streak logic ---
            val lastDay = prefs[LAST_ACTIVE_DAY] ?: -1L
            val currentStreak = prefs[STREAK] ?: 0

            if (amount > 0) {
                // Only update streak/day when earning XP
                prefs[STREAK] = when {
                    lastDay == todayEpochDay -> currentStreak          // same day — keep streak
                    lastDay == todayEpochDay - 1 -> currentStreak + 1 // consecutive day — extend
                    else -> 1                                          // gap — reset to 1
                }
                prefs[LAST_ACTIVE_DAY] = todayEpochDay

                // Reset xpToday if it's a new day
                if (lastDay != todayEpochDay) prefs[XP_TODAY] = 0

                prefs[XP_TODAY] = (prefs[XP_TODAY] ?: 0) + amount
                prefs[TASKS_DONE_TOTAL] = (prefs[TASKS_DONE_TOTAL] ?: 0) + 1
            } else {
                // Un-checking a task — subtract XP, don't touch streak
                val newXpToday = ((prefs[XP_TODAY] ?: 0) + amount).coerceAtLeast(0)
                prefs[XP_TODAY] = newXpToday
                prefs[TASKS_DONE_TOTAL] = ((prefs[TASKS_DONE_TOTAL] ?: 0) - 1).coerceAtLeast(0)
            }

            // totalXp
            val newTotal = ((prefs[TOTAL_XP] ?: 0) + amount).coerceAtLeast(0)
            prefs[TOTAL_XP] = newTotal
        }
    }

    suspend fun resetProgress() {
        context.dataStore.edit { prefs ->
            prefs[TOTAL_XP]         = 0
            prefs[XP_TODAY]         = 0
            prefs[STREAK]           = 0
            prefs[LAST_ACTIVE_DAY]  = -1L
            prefs[TASKS_DONE_TOTAL] = 0
            prefs[USER_NAME]        = "Arathorn"
            prefs[USER_CLASS]       = "wizard"
        }
    }

    private fun todayEpochDay(): Long {
        val cal = Calendar.getInstance()
        cal.set(Calendar.HOUR_OF_DAY, 0)
        cal.set(Calendar.MINUTE, 0)
        cal.set(Calendar.SECOND, 0)
        cal.set(Calendar.MILLISECOND, 0)
        return cal.timeInMillis / (1000 * 60 * 60 * 24)
    }
}