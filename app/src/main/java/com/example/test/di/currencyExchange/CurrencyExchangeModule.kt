package com.example.test.di.currencyExchange


import com.example.test.model.repositories.currency.CurrencyDataSource
import com.example.test.model.repositories.currency.CurrencyRepository
import com.example.test.presentation.usecase.LoadCurrenciesInteractor
import dagger.Binds
import dagger.Module
import dagger.Provides


@Module
abstract class CurrencyExchangeModule {

    @Binds
    abstract fun provideCurrenciesRepository(currenciesRepository: CurrencyRepository) : CurrencyDataSource

    @Module companion object {

        @Provides
        @CurrencyExchangeScope
        fun provideCurrenciesInteractor(currencyRepository: CurrencyDataSource): LoadCurrenciesInteractor {

            return LoadCurrenciesInteractor(currencyRepository)
        }

    }
}