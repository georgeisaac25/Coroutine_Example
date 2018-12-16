package com.example.georgeissac.mvplearnarch

import com.example.data.remote.retrofit.ApiInterface
import com.example.georgeissac.mvplearnarch.remote.retrofit.responseForCountry.Country
import kotlinx.coroutines.Deferred

class RemoteDataSource(var apiInterface: ApiInterface) {

    suspend fun getCountriesUsingMaybe(): List<Country> {
        return apiInterface.getCountriesUsingCourotines().await()
    }
}