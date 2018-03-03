package com.example.sarmadabdulrahman.machinelearning

/**
 * Created by Sarmad.Abdulrahman on 3/2/2018.
 */
 class DataContainer ( val Results:List<ReadRowResponse>)

class ReadRowResponse(
        val Count: Int,
        val Make:String,
        val Manufacturer:String,
        val Model:String,
        val ModelYear:String


)


