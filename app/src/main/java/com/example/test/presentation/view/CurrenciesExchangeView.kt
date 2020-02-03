package com.example.test.presentation.view

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.test.presentation.model.RateLocalModel

interface CurrenciesExchangeView : MvpView{

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun updateRates(remoteRateLocals: List<RateLocalModel>)

}