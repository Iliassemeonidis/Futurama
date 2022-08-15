package com.example.mytest.repositiry.datasourse.local

import com.example.mytest.model.AppState

interface DataSoursLocal<T> {
    suspend fun saveToDb(appState: AppState)
    suspend fun getFromDb(): List<T>
}