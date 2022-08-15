package com.example.mytest.repositiry.interactor

import com.example.mytest.model.AppState
import com.example.mytest.model.data.FuturamaResultData
import com.example.mytest.repositiry.repository.MainRepository

class MainInteractor(
    private val repository: MainRepository<FuturamaResultData>,
    private val connectivity: Connectivity
) : Interactor<AppState> {

    override suspend fun getData(): AppState {
        val appState: AppState
        if (connectivity.hasConnectivity()) {
            appState = AppState.Success(repository.getData())
            repository.saveToDb(appState)
        } else {
            appState = chekDataFomDb()
        }
        return appState
    }

    private suspend fun chekDataFomDb(): AppState {
        return if (repository.getFromDb().isNotEmpty()) {
            AppState.Success(repository.getFromDb())
        } else {
              AppState.Information("Отсутствуют данные в базе, необходимо кодключение к интернету")
        }
    }

    interface Connectivity {
        fun hasConnectivity(): Boolean
    }
}
