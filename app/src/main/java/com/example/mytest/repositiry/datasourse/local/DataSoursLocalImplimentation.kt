package com.example.mytest.repositiry.datasourse.local

import com.example.mytest.model.AppState
import com.example.mytest.model.data.FuturamaResultData

class DataSoursLocalImplimentation(private val remote: RoomDataBaseImplementation) : DataSoursLocal<FuturamaResultData> {

    override suspend fun saveToDb(appState: AppState) {
        remote.saveToDb(appState)
    }

    override suspend fun getFromDb(): List<FuturamaResultData> {
        return remote.getFromDb()
    }

}