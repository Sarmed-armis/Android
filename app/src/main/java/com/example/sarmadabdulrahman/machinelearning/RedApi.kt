package com.example.sarmadabdulrahman.machinelearning
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
/**
 * Created by Sarmad.Abdulrahman on 2/21/2018.
 * get work with that data
 * https://vpic.nhtsa.dot.gov/api/vehicles/decodevinvalues/1NXBR32E85Z505904?format=json
 */
interface RedApi {
    @GET("api/vehicles/decodevinvalues/{VIN}?format=json")
    fun getTop(@Path("VIN")VIN:String): Call<DataContainer>;

}