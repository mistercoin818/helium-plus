package com.example.madcampweek1.ui.weather

import com.example.madcampweek1.ui.weather.AirQualityResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface AirQualityService {

    @GET("nearest_city")
    fun getAirQualityData(@Query("lat") lat : String, @Query("lon") lon : String, @Query("key") key : String ) : Call<AirQualityResponse>

}
