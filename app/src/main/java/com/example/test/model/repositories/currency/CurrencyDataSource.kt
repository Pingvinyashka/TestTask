package com.example.test.model.repositories.currency



import com.example.test.model.repositories.currency.local.CurrencyAdditionalData
import com.example.test.model.repositories.currency.remote.CurrencyRemoteModel
import io.reactivex.Single


interface CurrencyDataSource {

    fun loadCurrencies(currency: String) : Single<CurrencyRemoteModel>

    fun getAdditionalData(): Single<Array<CurrencyAdditionalData>>

}