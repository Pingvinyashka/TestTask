package com.example.test.model.repositories.currency

import com.example.test.di.currencyExchange.CurrencyExchangeScope
import com.example.test.model.repositories.currency.local.CurrencyAdditionalData
import com.example.test.model.repositories.currency.local.CurrencyLocalDataSource
import com.example.test.model.repositories.currency.remote.CurrencyRemoteDataSource
import com.example.test.model.repositories.currency.remote.CurrencyRemoteModel
import io.reactivex.Single
import javax.inject.Inject

@CurrencyExchangeScope
class CurrencyRepository @Inject constructor(
    private val currenciesRemoteDataSource: CurrencyRemoteDataSource,
    private val currenciesLocalDataSource: CurrencyLocalDataSource
) : CurrencyDataSource {


    override fun loadCurrencies(currency: String): Single<CurrencyRemoteModel> {
        return currenciesRemoteDataSource.loadCurrencies(currency)
    }


    override fun getAdditionalData(): Single<Array<CurrencyAdditionalData>> {
        return currenciesLocalDataSource.getAdditionalData()
    }
}