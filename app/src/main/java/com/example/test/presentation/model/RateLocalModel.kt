package com.example.test.presentation.model

import com.example.test.model.repositories.currency.local.CurrencyAdditionalData
import com.example.test.model.repositories.currency.remote.CurrencyRemoteModel
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.SingleSource
import io.reactivex.SingleTransformer

data class RateLocalModel(
    val code: String,
    var value: String,
    var description: String,
    var flag: Int,
    var active: Boolean = false
)

class RateLocalTransformer :
    SingleTransformer<Pair<CurrencyRemoteModel, Array<CurrencyAdditionalData>>, List<RateLocalModel>> {

    override fun apply(upstream: Single<Pair<CurrencyRemoteModel, Array<CurrencyAdditionalData>>>): SingleSource<List<RateLocalModel>> {

        var base = ""

        return upstream.doOnSuccess { pair ->
            base = pair.first.base ?: ""
        }
            .flatMap { pair ->

                Observable.fromIterable(pair.first.rates?.entries)
                    .map { entry ->

                        val additionalData = pair.second.first { it.code == entry.key }

                        RateLocalModel(
                            entry.key,
                            entry.value,
                            additionalData.description,
                            additionalData.flagId,
                            false
                        )
                    }
                    .toList()
                    .doOnSuccess {
                        val additionalData = pair.second.first { it.code == base }
                        it.add(
                            0,
                            RateLocalModel(
                                base,
                                "1",
                                additionalData.description,
                                additionalData.flagId,
                                true
                            )
                        )
                    }
            }
    }
}

