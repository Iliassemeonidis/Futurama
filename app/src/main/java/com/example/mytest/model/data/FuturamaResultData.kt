package com.example.mytest.model.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class FuturamaResultData(
    @field:SerializedName("character") val character: String?,
    @field:SerializedName("quote") val quote: String?,
    @field:SerializedName("image") val image: String?
) : Serializable
