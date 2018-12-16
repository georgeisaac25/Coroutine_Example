package com.example.data.remote.retrofit

import com.example.georgeissac.mvplearnarch.remote.retrofit.responseForCountry.Country
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("rest/v2/all/")
    fun getCountries() : Call<List<Country>>

    /*@GET("rest/v2/all/")
    fun getCountriesUsingRx() : Maybe<List<Country>>*/

    @GET("rest/v2/all/")
    fun getCountriesUsingCourotines() : Deferred<List<Country>>

}