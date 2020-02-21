package com.example.test

import com.example.test.model.repositories.currency.local.CurrencyAdditionalData
import com.example.test.model.repositories.currency.remote.CurrencyRemoteModel
import com.example.test.presentation.model.RateLocalModel
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.util.*


class CurrencyConverTests {

    private lateinit var firstCurrency: RateLocalModel
    private lateinit var secondCurrency: RateLocalModel
    private lateinit var thirdCurrency: RateLocalModel
    private lateinit var currency: String

    @Before
    @Throws(Exception::class)
    fun setUp() {
        firstCurrency = RateLocalModel("EUR", "1")
        firstCurrency = RateLocalModel("RUB", "3.452")
        thirdCurrency = RateLocalModel("IND", "25.000")
    }

    @Test
    @Throws(Exception::class)
    fun test() {



    }

    @After
    @Throws(Exception::class)
    fun check() {
        mMath = null
    }


}