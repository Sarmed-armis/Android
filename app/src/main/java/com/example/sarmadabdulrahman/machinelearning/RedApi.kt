package com.example.sarmadabdulrahman.machinelearning
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
/**
 * Created by Sarmad.Abdulrahman on 2/21/2018.
 * get work with that data
 */
interface RedApi {
    @GET("/top.json")
    fun getTop(@Query("after") after: String,
               @Query("limit") limit: String)
            : Call<ReadDataResponse>;

}