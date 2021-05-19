package com.example.boomtown.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "https://swapi.dev/api/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface SWApiService{

    @GET("people/")
    suspend fun getPeopleList(): Record

    @GET("people/{id}")
    suspend fun getPerson( @Path("id") peopleId:Int): Character

}

object swapi {
    val retrofitService : SWApiService by lazy {
        retrofit.create(SWApiService::class.java)
    }
}