package com.example.test

import android.app.Application
import com.example.test.di.DaggerDataComponent
import com.example.test.di.DataComponent
import com.example.test.di.DataModule
import com.example.test.di.RetrofitModule

class ApplicationClass : Application(){

    companion object {

        lateinit var instanse: ApplicationClass
        lateinit var dataComponent: DataComponent

    }

    override fun onCreate() {
        super.onCreate()
        instanse = this
        initializeDaggerGraph()
    }


    private fun initializeDaggerGraph(){

            dataComponent = DaggerDataComponent.builder()
                .dataModule(DataModule(this))
                .retrofitModule(
                    RetrofitModule(
                        this,
                        this
                    )
                )
                .build()
    }

}