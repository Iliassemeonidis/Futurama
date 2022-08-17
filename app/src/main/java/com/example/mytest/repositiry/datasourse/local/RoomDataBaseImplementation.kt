package com.example.mytest.repositiry.datasourse.local

import com.example.mytest.model.AppState
import com.example.mytest.model.data.FuturamaResultData
import com.example.mytest.repositiry.room.HistoryDao
import com.example.mytest.repositiry.room.HistoryEntity

class RoomDataBaseImplementation(private val historyDao: HistoryDao) : DataSoursLocal<FuturamaResultData> {

    override suspend fun saveToDb(appState: AppState) {
        when (appState) {
            is AppState.Success ->{
               val data = appState.data
                data.forEach {
                    historyDao.insert(HistoryEntity(0,it.character,it.quote,it.image))
                }
            }
            else -> {}
        }
    }

    override suspend fun getFromDb(): List<FuturamaResultData> {
    val list:ArrayList<FuturamaResultData> = arrayListOf()

        historyDao.getAll().forEach {
            list.add(FuturamaResultData(it.character,it.quote,it.image))
        }
        return list
    }
}
