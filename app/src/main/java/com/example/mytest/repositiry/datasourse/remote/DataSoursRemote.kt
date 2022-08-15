package com.example.mytest.repositiry.datasourse.remote

interface DataSoursRemote<T> {
    suspend fun getData(): List<T>
}
