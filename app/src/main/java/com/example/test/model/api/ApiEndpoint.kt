package com.example.test.model.api

import com.example.test.model.repositories.currency.remote.CurrencyRemoteModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiEndpoint {

    @GET("/latest")
    fun getCurrency(@Query("base") base: String): Single<CurrencyRemoteModel>

}