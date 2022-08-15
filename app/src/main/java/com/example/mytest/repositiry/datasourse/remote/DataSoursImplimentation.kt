package com.example.mytest.repositiry.datasourse.remote

import com.example.mytest.model.data.FuturamaResultData
import com.example.mytest.repositiry.RetrofitBuilder

class DataSoursImplimentation(private val retrofitBuilder: RetrofitBuilder) :
    DataSoursRemote<FuturamaResultData> {

    override suspend fun getData(): List<FuturamaResultData> {
        return retrofitBuilder.getData()
    }
}