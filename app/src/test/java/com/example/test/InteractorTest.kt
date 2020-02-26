package com.example.test


import com.example.test.presentation.model.RateLocalModel
import com.example.test.presentation.usecase.LoadCurrenciesInteractor
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Before
import org.junit.Test

import java.util.concurrent.TimeUnit


class InteractorTest {

    private lateinit var currencyRepository: CurrencyRepositoryMock
    private lateinit var loadCurrencyInteractor : LoadCurrenciesInteractor

    private val initialCurrency = RateLocalModel(
        "EUR",
        "1",
        "Euro",
        R.drawable.ic_flag_eur,
        true
    )

    @Before
    @Throws(Exception::class)
    fun setUp() {

        currencyRepository = CurrencyRepositoryMock()
        loadCurrencyInteractor = LoadCurrenciesInteractor(currencyRepository)
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    }

    @Test
    @Throws(Exception::class)
    fun test() {

        val testSubscriber = loadCurrencyInteractor.getCurrencies(initialCurrency).test()

        testSubscriber.awaitTerminalEvent(1, TimeUnit.SECONDS)
        testSubscriber.assertNoErrors()
        testSubscriber.assertValueCount(1)

        val values: List<RateLocalModel> = testSubscriber.values()[0]

        assert(values.isNotEmpty()){"empty list"}
        assert(values.map { it.active }.any { true }){"no one code is active"}

        values.forEach {rate ->

            assert(rate.code.isNotEmpty()){"empty code after transforming data"}
            assert(rate.value.isNotEmpty()){"empty value after transforming data"}

        }
    }

    @After
    @Throws(Exception::class)
    fun check() {

    }


}