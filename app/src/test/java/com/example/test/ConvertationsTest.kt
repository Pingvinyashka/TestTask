package com.example.test


import com.example.test.presentation.model.RateLocalModel
import com.example.test.presentation.usecase.Convertation
import org.junit.After
import org.junit.Before
import org.junit.Test


class ConvertationsTest {

    private lateinit var convertationClass: Convertation
    private lateinit var oldValue: RateLocalModel
    private lateinit var newValue: RateLocalModel


    @Before
    @Throws(Exception::class)
    fun setUp() {
        convertationClass = Convertation()
        oldValue = RateLocalModel("EUR", "1.5")
        newValue = RateLocalModel("RUB", "1.2")
    }

    @Test
    @Throws(Exception::class)
    fun test() {

        val firstDiff = convertationClass.convertReceived(oldValue, newValue)
        val secondDiff = convertationClass.convertReceived(newValue, oldValue)

        assert(firstDiff.isNotEmpty())
        assert(secondDiff.isNotEmpty())
        assert(firstDiff==secondDiff)
    }

    @After
    @Throws(Exception::class)
    fun check() {

    }


}