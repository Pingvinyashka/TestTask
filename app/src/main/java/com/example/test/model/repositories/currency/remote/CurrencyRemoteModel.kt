package com.example.test.model.repositories.currency.remote

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CurrencyRemoteModel(
    val base: String? = null,
    val date: String? = null,
    val rates: Map<String,String>? = null
)