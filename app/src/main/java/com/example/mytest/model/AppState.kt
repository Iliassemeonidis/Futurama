package com.example.mytest.model

import com.example.mytest.model.data.FuturamaResultData

sealed class AppState {
    data class Success(val data: List<FuturamaResultData>) : AppState()
    data class Error(val error: Throwable) : AppState()
    data class Loading(val progress: Int) : AppState()
    data class Information(val message: String) : AppState()
}