package com.example.sarmadabdulrahman.machinelearning

import android.util.Log
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
                .baseUrl("https://vpic.nhtsa.dot.gov")
                .addConverterFactory(MoshiConverterFactory.create())
                .build()

        redditApi = retrofit.create(RedApi::class.java)
    }

    fun getNews(VIN:String): Call<DataContainer> {
        //Log.i("The REDD:-",redditApi.getTop("1NXBR32E85Z505904").execute().message())

        return redditApi.getTop(VIN)
    }
}