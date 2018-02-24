package com.example.sarmadabdulrahman.machinelearning

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by Sarmad.Abdulrahman on 2/21/2018.
 */
class RestApi(){

    private val redditApi: RedApi
    init {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://www.reddit.com")
                .addConverterFactory(MoshiConverterFactory.create())
                .build()

        redditApi = retrofit.create(RedApi::class.java)
    }

    fun getNews(after:String,limit:String): Call<ReadDataResponse> {
        return redditApi.getTop(after,limit)
    }
}