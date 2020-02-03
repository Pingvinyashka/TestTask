package com.example.test.di

import android.content.Context
import android.content.SharedPreferences
import com.example.test.R
import dagger.Module
import dagger.Provides


import javax.inject.Singleton

@Module
class DataModule(private val context: Context) {

    private val sharedPreferences: SharedPreferences
        get() = context.getSharedPreferences(context.getString(R.string.app_settings), Context.MODE_PRIVATE)

    @Provides
    @Singleton
    fun provideApplicationContext(): Context {
        return context
    }

    @Provides
    @Singleton
    fun provideApplicationSharedPreferences(): SharedPreferences {
        return sharedPreferences
    }
}