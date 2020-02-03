package com.example.test.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.test.ApplicationClass
import com.example.test.R
import com.example.test.di.currencyExchange.DaggerCurrencyExchangeComponent
import com.example.test.presentation.usecase.LoadCurrenciesInteractor
import com.example.test.presentation.model.RateLocalModel

import com.example.test.presentation.view.CurrenciesExchangeView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@InjectViewState
class CurrenciesExchangePresenter : MvpPresenter<CurrenciesExchangeView>() {

    @Inject
    lateinit var loadCurrenciesInteractor: LoadCurrenciesInteractor

    private var compositeDisposable = CompositeDisposable()
    private var publishSubject = PublishSubject.create<RateLocalModel>()
    private val initialCurrency = RateLocalModel(
        "EUR",
        "1",
        "Euro",
        R.drawable.ic_flag_eur,
        true
    )
    private var started = false

    init {
        DaggerCurrencyExchangeComponent.builder()
            .dataComponent(ApplicationClass.dataComponent)
            .build().inject(this)
    }


    fun init() {
        if (!started) {
            loadCurrencies(initialCurrency)
            started = true
        }
    }

    private fun loadCurrencies(base: RateLocalModel) {

        publishSubject.switchMap { rateModel ->

            loadCurrenciesInteractor
                .getCurrencies(rateModel)
                .repeatWhen { it.delay(1, TimeUnit.SECONDS) }
        }
            .subscribe({
                viewState.updateRates(it)
            }, {
                it.printStackTrace()
            }).also { compositeDisposable.add(it) }

        publishSubject.onNext(base)

    }


    fun changeValue(remoteRateLocal: RateLocalModel, value: CharSequence) {
        remoteRateLocal.value = value.toString()
        publishSubject.onNext(remoteRateLocal)
    }

    fun swapPositions(remoteRateLocal: RateLocalModel) {
        publishSubject.onNext(remoteRateLocal)
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }
}