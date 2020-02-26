package com.example.test

import com.example.test.model.repositories.currency.CurrencyDataSource
import com.example.test.model.repositories.currency.local.CurrencyAdditionalData
import com.example.test.model.repositories.currency.remote.CurrencyRemoteModel
import io.reactivex.Single
import java.util.*
import kotlin.collections.HashMap

class CurrencyRepositoryMock : CurrencyDataSource {

    override fun loadCurrencies(currency: String): Single<CurrencyRemoteModel> {
        return getMockedCurrencies(currency)
    }

    override fun getAdditionalData(): Single<Array<CurrencyAdditionalData>> {
        return Single.just(CurrencyAdditionalData.values())
    }

    fun getMockedCurrencies(currency: String): Single<CurrencyRemoteModel> {

        val currencyMap =  HashMap<String,String>()

        currencyMap[currency] = "1"
        currencyMap["AUD"] = "1234"
        currencyMap["BGN"] = "250"
        currencyMap["BRL"] = "1.2"
        currencyMap["CZK"] = "1.3"

        return Single.just(CurrencyRemoteModel(
            currency,
            Date().toString(),
            currencyMap
        ))

    }
}