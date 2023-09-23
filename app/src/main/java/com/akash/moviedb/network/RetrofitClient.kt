package com.akash.moviedb.network

import com.akash.moviedb.BuildConfig
import com.akash.moviedb.api.SingleMovieDetailsAPI
import com.akash.moviedb.api.SingleTVShowDetailsAPI
import com.akash.moviedb.api.TrendingMoviesAPI
import com.akash.moviedb.api.TrendingTVShowsAPI
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    private var retrofit: Retrofit? = null
    private val builder = OkHttpClient.Builder()
    private val interceptor = HttpLoggingInterceptor()

    fun getRetrofitInstance(): Retrofit {
        val dispatcher = Dispatcher()
        dispatcher.maxRequests = 1

        builder.callTimeout(50, TimeUnit.SECONDS)
        builder.connectTimeout(50, TimeUnit.SECONDS)
        builder.readTimeout(50, TimeUnit.SECONDS)
        builder.writeTimeout(50, TimeUnit.SECONDS)

        val gson: Gson = GsonBuilder().setLenient().create()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        builder.addInterceptor(interceptor)

        if (retrofit == null) {
            retrofit = Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(builder.dispatcher(dispatcher).build()).build()
        }

        return retrofit!!
    }

    fun getTrendingMoviesInterfaceService(): TrendingMoviesAPI {
        return getRetrofitInstance().create(TrendingMoviesAPI::class.java)
    }

    fun getTrendingTVShowsInterfaceService(): TrendingTVShowsAPI {
        return getRetrofitInstance().create(TrendingTVShowsAPI::class.java)
    }

    fun getSingleMovieInterfaceService(): SingleMovieDetailsAPI {
        return getRetrofitInstance().create(SingleMovieDetailsAPI::class.java)
    }

    fun getSingleTVShowInterfaceService(): SingleTVShowDetailsAPI {
        return getRetrofitInstance().create(SingleTVShowDetailsAPI::class.java)
    }
}

//https://api.themoviedb.org/3/trending/movie/day?language=en-US&api_key=c33832f707ec95387239c7014b8fb76b
//https://api.themoviedb.org/3/trending/tv/day?language=en-US&api_key=c33832f707ec95387239c7014b8fb76b
//https://api.themoviedb.org/3/movie/820609?api_key=c33832f707ec95387239c7014b8fb76b
//https://api.themoviedb.org/3/tv/72710?api_key=c33832f707ec95387239c7014b8fb76b