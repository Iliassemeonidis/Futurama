package com.example.mytest.repositiry.interactor

interface Interactor<T> {
    suspend fun getData() : T
}