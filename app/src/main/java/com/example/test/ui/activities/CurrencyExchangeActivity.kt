package com.example.test.ui.activities


import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.test.R
import com.example.test.model.adapters.CurrencyAdapter
import com.example.test.presentation.presenter.CurrenciesExchangePresenter
import com.example.test.presentation.model.RateLocalModel
import com.example.test.presentation.view.CurrenciesExchangeView
import kotlinx.android.synthetic.main.activity_currency_exchange.*


class CurrencyExchangeActivity : MvpAppCompatActivity(), CurrenciesExchangeView {

    @InjectPresenter
    lateinit var presenter: CurrenciesExchangePresenter

    lateinit var adapter: CurrencyAdapter

    private val currencyChanges =
        { rateLocalModel: RateLocalModel, value: CharSequence ->
            presenter.changeValue(rateLocalModel, value)
        }

    private val swap =
        { rateLocalModel: RateLocalModel -> presenter.swapPositions(rateLocalModel) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_currency_exchange)

        init()
        presenter.init()
    }


    private fun init() {
        adapter = CurrencyAdapter(currencyChanges, swap)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = adapter
    }


    override fun updateRates(remoteRateLocals: List<RateLocalModel>) {
        adapter.updateRates(remoteRateLocals)
    }
}
