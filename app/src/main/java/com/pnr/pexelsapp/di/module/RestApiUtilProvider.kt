package com.pnr.pexelsapp.di.module

import android.content.Context
import com.pnr.pexelsapp.BuildConfig
import com.pnr.pexelsapp.restclient.interfaces.PexelsPhotosApis
import com.pnr.pexelsapp.util.Constants
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class RestApiUtilProvider {
    /**
     * provides OkHttpClient
     *
     * @param context
     * @return
     */
    @Provides
    @Singleton
    internal fun provideOkHttpClient(context: Context): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
            okHttpClientBuilder.addInterceptor(httpLoggingInterceptor)
        }
        //to cache the network response
        val cache = Cache(context.cacheDir, Constants.CACHE_SIZE)
        return okHttpClientBuilder
            .connectTimeout(Constants.API_CONNECT_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
            .readTimeout(Constants.API_READ_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(Constants.API_WRITE_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
            .cache(cache)
            .build()
    }

    /**
     * provides Retrofit Instance
     *
     * @param okHttpClient
     * @return
     */
    @Provides
    @Singleton
    internal fun provideRetrofitInstance(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    /**
     * provides ApiService
     *
     * @param retrofit
     * @return
     */
    @Provides
    @Singleton
    internal fun provideApiService(retrofit: Retrofit): PexelsPhotosApis {
        return retrofit.create(PexelsPhotosApis::class.java)
    }
}