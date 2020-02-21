package com.example.test

import android.view.View
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.agoda.kakao.common.views.KView
import com.agoda.kakao.edit.KEditText
import com.agoda.kakao.image.KImageView
import com.agoda.kakao.recycler.KRecyclerItem
import com.agoda.kakao.recycler.KRecyclerView
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KButton
import com.agoda.kakao.text.KTextView
import com.example.test.ui.activities.CurrencyExchangeActivity
import org.hamcrest.Matcher
import ru.spb.niias.mrmplan.KScreen


object CurrencyExchangeScreen  : KScreen<CurrencyExchangeScreen>(){

    override val layoutId: Int? = R.layout.activity_currency_exchange
    override val viewClass: Class<*>? = CurrencyExchangeActivity::class.java



    val recycler: KRecyclerView = KRecyclerView({
        withId(R.id.recyclerView)
    }, itemTypeBuilder = {
        itemType(::Item)
    })


    class Item(parent: Matcher<View>) : KRecyclerItem<Item>(parent) {
        val currencyCode = KTextView(parent) { withId(R.id.currencyCodeTxt) }
        val currencyFlag = KImageView(parent) { withId(R.id.currencyFlagImg) }
        val currencyInput = KEditText(parent) { withId(R.id.currencyInputTxt) }
        val self = KView {withMatcher(parent)}

    }

}





/*
class SearchResultsScreen : Screen<SearchResultsScreen>() {
    val recycler: KRecyclerView = KRecyclerView({
        withId(R.id.recyclerView)
    }, itemTypeBuilder = {
        itemType(::Item)
    })
}*/
