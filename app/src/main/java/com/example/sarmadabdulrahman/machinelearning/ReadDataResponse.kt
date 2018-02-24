package com.example.sarmadabdulrahman.machinelearning

/**
 * Created by Sarmad.Abdulrahman on 2/21/2018.
 */
class ReadDataResponse(val data:ReadRowResponse)

class ReadRowResponse(
    val children: List<RedditChildrenResponse>,
    val after: String?,
    val before: String?)

class RedditChildrenResponse(val data: ReadRowNewsResponse)

class ReadRowNewsResponse(
        val author: String,
        val title: String,
        val num_comments: Int,
        val created: Long,
        val thumbnail: String,
        val url: String


)