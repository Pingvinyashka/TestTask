package com.example.test.di.currencyExchange

import com.example.test.di.DataComponent
import com.example.test.presentation.presenter.CurrenciesExchangePresenter
import dagger.Component


@Component(dependencies = [DataComponent::class],modules = [CurrencyExchangeModule::class])
@CurrencyExchangeScope
interface CurrencyExchangeComponent{

    fun inject(currenciesExchangePresenter: CurrenciesExchangePresenter)

}