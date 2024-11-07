package com.coolkosta.effectivemobiletestapp.kotlin

import android.content.SharedPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun SharedPreferences.saveData(
    isCommit: Boolean,
    key: String,
    value: Any,
    onSuccess: () -> Unit,
    onFailure: (Exception) -> Unit
) {
    withContext(Dispatchers.IO) {
        try {
            val editor = edit()
            when (value) {
                is Boolean -> editor.putBoolean(key, (value))
                is Int -> editor.putInt(key, (value))
                is Float -> editor.putFloat(key, (value))
                is Long -> editor.putLong(key, (value))
                is String -> editor.putString(key, (value))
                else -> throw IllegalArgumentException("Unsupported type")
            }
            if (isCommit) editor.commit() else editor.apply()
            onSuccess()
        } catch (e: Exception) {
            onFailure(e)
        }
    }
}

suspend inline fun <reified T : Any> SharedPreferences.getData(
    key: String,
    defaultValue: T,
): Result<T> {
    return withContext(Dispatchers.IO) {
        try {
            val data = when (defaultValue) {
                is Boolean -> {
                    getBoolean(key, defaultValue) as T
                }

                is Int -> {
                    getInt(key, defaultValue) as T
                }

                is Float -> {
                    getFloat(key, defaultValue) as T
                }

                is Long -> {
                    getLong(key, defaultValue) as T
                }

                is String -> {
                    getString(key, defaultValue) as T
                }

                else -> {
                    throw IllegalArgumentException(
                        "Can't get data of type ${T::class.java}"
                    )
                }
            }
            Result.success(data)
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }
}

