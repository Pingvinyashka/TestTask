package com.example.test


import com.example.test.model.api.ApiEndpoint
import com.example.test.model.repositories.currency.remote.CurrencyRemoteDataSource
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations


class ServiceTest {

    private var currencyRepository: CurrencyRepositoryMock = CurrencyRepositoryMock()

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun getAllPosts() {

        val postService = mock(ApiEndpoint::class.java)
        val postsRemoteDataSource = CurrencyRemoteDataSource(postService)

        Mockito.`when`(postService.getCurrency("EUR")).thenReturn(
            (currencyRepository.getMockedCurrencies(
                "EUR"
            ))
        )

        val testSubscriber = postsRemoteDataSource.loadCurrencies("EUR").test()

        testSubscriber.assertComplete()
        testSubscriber.assertValueCount(1)
        testSubscriber.assertNoErrors()

    }


    @After
    @Throws(Exception::class)
    fun check() {

    }


}