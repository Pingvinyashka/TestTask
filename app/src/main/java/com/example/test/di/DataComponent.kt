package com.example.test.di


import android.content.Context
import com.example.test.model.api.ApiEndpoint
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(DataModule::class, RetrofitModule::class))
interface DataComponent {

    fun context(): Context
    fun api(): ApiEndpoint

}