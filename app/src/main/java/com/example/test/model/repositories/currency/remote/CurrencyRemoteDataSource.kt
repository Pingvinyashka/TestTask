package com.example.test.model.repositories.currency.remote

import com.example.test.model.api.ApiEndpoint
import com.example.test.di.currencyExchange.CurrencyExchangeScope
import com.example.test.model.repositories.currency.CurrencyDataSource
import com.example.test.model.repositories.currency.local.CurrencyAdditionalData
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@CurrencyExchangeScope
class CurrencyRemoteDataSource @Inject constructor(
    private val api: ApiEndpoint
) : CurrencyDataSource{

    override fun loadCurrencies(currency: String): Single<CurrencyRemoteModel> {

        return api.getCurrency(currency)

    }

    override fun getAdditionalData(): Single<Array<CurrencyAdditionalData>> {
        return Single.error(Throwable())
    }
}