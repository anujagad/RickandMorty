package com.example.sdo_task.di

import com.example.sdo_task.viewModel.MainActivityViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RetroModule::class])
interface RetroComponent {
    fun inject(viewModel: MainActivityViewModel)
}