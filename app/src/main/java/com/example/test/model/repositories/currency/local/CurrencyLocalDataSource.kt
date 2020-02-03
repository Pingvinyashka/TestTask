package com.example.test.model.repositories.currency.local

import com.example.test.R
import com.example.test.di.currencyExchange.CurrencyExchangeScope
import com.example.test.model.repositories.currency.CurrencyDataSource
import com.example.test.model.repositories.currency.remote.CurrencyRemoteModel
import io.reactivex.Single
import javax.inject.Inject


@CurrencyExchangeScope
class CurrencyLocalDataSource @Inject constructor() : CurrencyDataSource {


    override fun loadCurrencies(currency: String): Single<CurrencyRemoteModel> {
        return Single.error(Throwable())
    }

    override fun getAdditionalData() = Single.just(CurrencyAdditionalData.values())
}


enum class CurrencyAdditionalData(
    val code: String,
    val description: String,
    val flagId: Int
){
    EUR("EUR","Euro", R.drawable.ic_flag_eur),
    AUD("AUD","Australian Dollar",R.drawable.ic_flag_australia),
    BGN("BGN","Bulgarian Lev",R.drawable.ic_flag_bulgaria),
    BRL("BRL","Brazilian Real",R.drawable.ic_flag_brazil),
    CAD("CAD","Canadian Dollar",R.drawable.ic_flag_canada),
    CHF("CHF","Swiss Franc",R.drawable.ic_flag_switzerland),
    CNY("CNY","Yuan Renminbi",R.drawable.ic_flag_china),
    CZK("CZK","Czech Koruna",R.drawable.ic_flag_czech),
    DKK("DKK","Danish Krone",R.drawable.ic_flag_denmark),
    GBP("GBP","Pound Sterling",R.drawable.ic_flag_uk),
    HKD("HKD","Hong Kong Dollar",R.drawable.ic_flag_hong_kong),
    HRK("HRK","Croatian Kuna",R.drawable.ic_flag_croatia),
    HUF("HUF","Forint",R.drawable.ic_flag_hungary),
    IDR("IDR","Rupiah",R.drawable.ic_flag_indonesia),
    ILS("ILS","New Israeli Sheqel",R.drawable.ic_flag_israel),
    INR("INR","Indian Rupee",R.drawable.ic_flag_india),
    ISK("ISK","Iceland Krona",R.drawable.ic_flag_iceland),
    JPY("JPY","Yen",R.drawable.ic_flag_japan),
    KRW("KRW","Won",R.drawable.ic_flag_south_korea),
    MXN("MXN","Mexican Peso",R.drawable.ic_flag_mexico),
    MYR("MYR","Malaysian Ringgit",R.drawable.ic_flag_malaysia),
    NOK("NOK","Norwegian Krone",R.drawable.ic_flag_norway),
    NZD("NZD","New Zealand Dollar",R.drawable.ic_flag_new_zealand),
    PHP("PHP","Philippine Peso",R.drawable.ic_flag_philippines),
    PLN("PLN","Zloty",R.drawable.ic_flag_poland),
    RON("RON","New Romanian Leu",R.drawable.ic_flag_romania),
    RUB("RUB","Russian Ruble",R.drawable.ic_flag_russia),
    SEK("SEK","Swedish Krona",R.drawable.ic_flag_sweden),
    SGD("SGD","Singapore Dollar",R.drawable.ic_flag_singapore),
    THB("THB","Baht",R.drawable.ic_flag_thailand),
    TRY("TRY","Turkish Lira",R.drawable.ic_flag_turkey),
    USD("USD","US Dollar",R.drawable.ic_flag_usa),
    ZAR("ZAR","Rand",R.drawable.ic_flag_south_africa)
}