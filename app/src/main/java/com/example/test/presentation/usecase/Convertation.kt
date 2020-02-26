package com.example.test.presentation.usecase

import com.example.test.extension.scaleTo
import com.example.test.presentation.model.RateLocalModel


class Convertation() {

    fun convertReceived(oldValue: RateLocalModel, newValue: RateLocalModel): String {
        return (newValue.value.toFloat() * oldValue.value.toFloat()).scaleTo(10)
    }

}