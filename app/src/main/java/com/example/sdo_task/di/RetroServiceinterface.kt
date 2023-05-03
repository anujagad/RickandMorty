package com.example.sdo_task.di

import com.example.sdo_task.model.Episode
import io.reactivex.Observable
import retrofit2.http.GET

interface RetroServiceinterface {
    @GET("api/character")
    fun getDataFromAPI(): Observable<Episode>
}