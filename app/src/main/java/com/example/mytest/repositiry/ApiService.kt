package com.example.mytest.repositiry

import com.example.mytest.model.data.FuturamaResultData
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface ApiService {

    @GET("quotes")
    fun getFuturamaDataAsync(): Deferred<List<FuturamaResultData>>
}