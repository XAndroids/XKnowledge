package com.android.xknowledge.jetpack.paging.api

import android.util.Log
import com.android.xknowledge.jetpack.paging.model.PagingData
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Retrofit网络请求
 */
interface RetrofitApi {

    @GET("/r/{subreddit}/hot.json")
    fun getTop(
        @Path("subreddit") subreddit: String,
        @Query("limit") limit: Int
    ): Call<ListingResponse>

    @GET("/r/{subreddit}/hot.json")
    fun getTopAfter(
        @Path("subreddit") subreddit: String,
        @Query("after") after: String,
        @Query("limit") limit: Int
    ): Call<ListingResponse>

    /**
     * 网络请求返回结果
     */
    class ListingResponse(val data: ListingData)

    class ListingData(
        val children: List<RedditChildrenResponse>,
        //FIXME 在itempage中使用，具体的作用是？
        val after: String?,
        val before: String?
    )

    data class RedditChildrenResponse(val data: PagingData)

    companion object {
        private const val BASE_URL = "https://www.reddit.com/"

        fun create(): RetrofitApi = create(HttpUrl.parse(BASE_URL)!!)

        fun create(httpUrl: HttpUrl): RetrofitApi {
            val logger = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
                Log.d("API", it)
            })
            logger.level = HttpLoggingInterceptor.Level.BASIC
            val client = OkHttpClient.Builder().addInterceptor(logger).build()
            return Retrofit.Builder().baseUrl(httpUrl).client(client)
                .addConverterFactory(GsonConverterFactory.create()).build()
                .create(RetrofitApi::class.java)
        }
    }
}
