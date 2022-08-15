package com.example.mytest.repositiry.repository

import com.example.mytest.model.AppState
import com.example.mytest.model.data.FuturamaResultData
import com.example.mytest.repositiry.datasourse.local.DataSoursLocal
import com.example.mytest.repositiry.datasourse.remote.DataSoursRemote

class RepositoryMainImplementation(
    private val dataSourceRemote: DataSoursRemote<FuturamaResultData>,
    private val dataSourceLocal: DataSoursLocal<FuturamaResultData>
) : MainRepository<FuturamaResultData> {

    override suspend fun getData(): List<FuturamaResultData> {
        return dataSourceRemote.getData()
    }

    override suspend fun saveToDb(appState: AppState) {
        dataSourceLocal.saveToDb(appState)
    }

    override suspend fun getFromDb(): List<FuturamaResultData> {
        return dataSourceLocal.getFromDb()
    }
}