package com.example.pinterest.network.service

import com.example.pinterest.model.Photo
import com.example.pinterest.model.RelatedPhotos
import com.example.pinterest.model.Search
import com.example.mypinterest.model.Topic
import com.example.pinterest.model.Collection
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface PhotoService {

    companion object {
        //my
//        private const val ACCESS_KEY = "i3sMlxvDQYCwLfcg9eH9I7cEI18u8z7OL51VWRbETP8"
        // Mr. X
        private const val ACCESS_KEY = "tJazrzFH2q4OV1hKlvfFYvCxzegtvjhUkRhNx6E6ekY"
    }

    @Headers("Authorization: Client-ID $ACCESS_KEY")
    @GET("photos")
    fun getPhotos(@Query("page") page: Int, @Query("per_page") per_page: Int): Call<ArrayList<Photo>>

    @Headers("Authorization: Client-ID $ACCESS_KEY")
    @GET("photos/{id}/related")
    fun getRelatedPhotos(@Path("id") id: String): Call<RelatedPhotos>

    @Headers("Authorization: Client-ID $ACCESS_KEY")
    @GET("search/photos")
    fun searchPhoto(@Query("query") query: String, @Query("page") page: Int, @Query("per_page") per_page: Int): Call<Search>

    @Headers("Authorization: Client-ID $ACCESS_KEY")
    @GET("topics")
    fun getTopics(@Query("page") page: Int = 2, @Query("per_page") per_page: Int = 10): Call<ArrayList<Topic>>


    @Headers("Authorization: Client-ID $ACCESS_KEY")
    @GET("collections")
    fun getCollections(@Query("page") page: Int = 2, @Query("per_page") per_page: Int = 10): Call<ArrayList<Collection>>



}