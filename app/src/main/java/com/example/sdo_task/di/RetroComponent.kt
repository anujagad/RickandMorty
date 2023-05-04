package com.example.sdo_task.di

import android.content.Context
import com.example.sdo_task.App
import com.example.sdo_task.fragments.CharacterListingFragment
import com.example.sdo_task.viewModel.MainActivityViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RetroModule::class])
interface RetroComponent {

    // Factory to create instances of the AppComponent
  /*  @Component.Factory
    interface Factory {
        // With @BindsInstance, the Context passed in will be available in the graph
        fun create(@BindsInstance context: Context): RetroComponent
    }*/
    fun inject(viewModel: MainActivityViewModel)

}