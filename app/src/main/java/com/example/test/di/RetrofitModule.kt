package com.example.test.di

import android.content.Context
import com.example.test.BuildConfig
import com.example.test.ApplicationClass
import com.example.test.model.api.ApiEndpoint

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory



import javax.inject.Singleton
import java.util.concurrent.TimeUnit

@Module
class RetrofitModule(
    private val context: Context,
    private val applicationClass: ApplicationClass
)
{

    @Provides
    @Singleton
    internal fun provideHttpCache(context: Context): Cache {
        val cacheSize = 10 * 1024 * 1024
        val cache = Cache(context.cacheDir, cacheSize.toLong())
        return cache
    }



    @Provides
    @Singleton
    internal fun provideMoshi(): Moshi {
        val moshiBuilder = Moshi.Builder()
                Moshi.Builder()
            .add(KotlinJsonAdapterFactory())

        return moshiBuilder.build()
    }



    @Provides
    @Singleton
    internal fun provideOkhttpClient(
        cache: Cache
    ): OkHttpClient {


        val interceptor = HttpLoggingInterceptor()
        interceptor.level =
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE

        val client = OkHttpClient.Builder()
        client
            .addInterceptor(interceptor)
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10,TimeUnit.SECONDS)
            .cache(cache)
        return client.build()
    }

    @Provides
    @Singleton
    internal fun provideRetrofit(moshy: Moshi, okHttpClient: OkHttpClient): ApiEndpoint {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshy))
            .baseUrl(BuildConfig.testHost)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create<ApiEndpoint>(ApiEndpoint::class.java)
    }

}
