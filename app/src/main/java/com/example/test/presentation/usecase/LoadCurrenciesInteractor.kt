package com.example.test.presentation.usecase


import com.example.test.di.currencyExchange.CurrencyExchangeScope
import com.example.test.model.repositories.currency.CurrencyDataSource
import com.example.test.model.repositories.currency.local.CurrencyAdditionalData
import com.example.test.model.repositories.currency.remote.CurrencyRemoteModel
import com.example.test.presentation.model.RateLocalModel
import com.example.test.presentation.model.RateLocalTransformer
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers

import javax.inject.Inject


@CurrencyExchangeScope
class LoadCurrenciesInteractor @Inject constructor(
    private val currencyRepository: CurrencyDataSource
) {

    fun getCurrencies(base: RateLocalModel): Observable<List<RateLocalModel>> {

        return loadCurrencies(base.code)
            .map { rateModelsList ->

                rateModelsList.forEach {
                    it.value = Convertation().convertReceived(it,base)
                }
                rateModelsList

            }.map { list ->
                list.sortedWith(compareBy({ !it.active }, { it.code }))
            }
            .toObservable()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    private fun loadCurrencies(base: String) =
        currencyRepository.loadCurrencies(base)
            .zipWith(
                currencyRepository.getAdditionalData(),
                BiFunction { remote: CurrencyRemoteModel, local: Array<CurrencyAdditionalData> ->
                    Pair(remote, local)
                })
            .compose(RateLocalTransformer())
}

