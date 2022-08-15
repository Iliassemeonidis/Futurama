package com.example.mytest.repositiry.repository

import com.example.mytest.model.AppState

interface MainRepository<T> {
    suspend fun getData(): List<T>
    suspend fun saveToDb(appState: AppState)
    suspend fun getFromDb() : List<T>
}