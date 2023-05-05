package com.example.sdo_task.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sdo_task.viewModel.MainActivityViewModel
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Module
class RetroModule {

    val baseUrl = "https://rickandmortyapi.com/"

    @Singleton
    @Provides
    fun getRetroServiceInterface(retrofit :Retrofit) :RetroServiceinterface{
        return retrofit.create(RetroServiceinterface::class.java)
    }

    @Singleton
    @Provides
    fun getRetrofitInstance():Retrofit{
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    }

    @Provides
    fun provideMyViewModelFactory(
        myRepository: RetroServiceinterface
    ): ViewModelProvider.Factory = MyViewModelFactory(myRepository)


}

class MyViewModelFactory @Inject constructor(
    private val myRepository: RetroServiceinterface
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainActivityViewModel(myRepository) as T
    }
}
