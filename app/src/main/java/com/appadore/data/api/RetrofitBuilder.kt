package com.appadore.data.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext


object RetrofitBuilder {
    private const val BASE_URL = "http://huddle-dev.messej.com/"
    private var sOkHttpClient: OkHttpClient? = null
    private var sslContext: SSLContext? = null
    private var sslSocketFactory: javax.net.ssl.SSLSocketFactory? = null
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getOkHttpClient()!!)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
    }

    private fun getOkHttpClient(): OkHttpClient? {
        sslSocketFactory = sslContext?.getSocketFactory()
        val okHttpClientBuilder = OkHttpClient().newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .addInterceptor { chain ->
                    val original = chain.request()
                    val request = original.newBuilder()
                             //.header("Content-Type", "application/x-www-form-urlencoded")
                            .method(original.method, original.body)
                            .build()
                    chain.proceed(request)
                }

        sOkHttpClient=okHttpClientBuilder.build()


        return sOkHttpClient
    }

    val apiService: ApiService = getRetrofit().create(ApiService::class.java)

}