package com.example.sdo_task

import android.app.Application
import com.example.sdo_task.di.DaggerRetroComponent
import com.example.sdo_task.di.RetroComponent
import com.example.sdo_task.di.RetroModule

class App : Application() {

    private lateinit var retroComponent: RetroComponent
    override fun onCreate() {
        super.onCreate()

        retroComponent = DaggerRetroComponent.builder()
            .retroModule(RetroModule())
            .build()
    }

    fun getRetroComponent():RetroComponent{
        return retroComponent
    }
}