package com.example.cocktailbible.network

import com.example.cocktailbible.AppConstants
import com.example.cocktailbible.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    private var retrofit: Retrofit? = null

    fun getRetrofitService(): Retrofit {

        if (retrofit == null) {

            var interceptorAuth: Interceptor = object : Interceptor {
                override fun intercept(chain: Interceptor.Chain): Response {
                    val newRequest: Request =
                        chain.request().newBuilder()
                            .addHeader("x-rapidapi-host", "the-cocktail-db.p.rapidapi.com")
                            .addHeader(
                                "x-rapidapi-key",
                                BuildConfig.COCKTAILSDB_API_KEY
                            )
                            .build()
                    return chain.proceed(newRequest)
                }

            }
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            val pool = ConnectionPool(100, 5, TimeUnit.MINUTES)

            val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(interceptorAuth)
                .connectionPool(pool)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build()

            val gson = GsonBuilder()
                .setLenient()
                .create()

            retrofit = Retrofit.Builder()
                .baseUrl(AppConstants.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

            return retrofit!!
        } else {
            return retrofit!!
        }
    }

}