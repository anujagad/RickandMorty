package com.example.sdo_task.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.sdo_task.App
import com.example.sdo_task.di.RetroServiceinterface
import com.example.sdo_task.model.Episode
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainActivityViewModel(application : Application):AndroidViewModel(application) {
    @Inject
    lateinit var mService :RetroServiceinterface
    private lateinit var liveDatalist : MutableLiveData<Episode?>
    private lateinit var showProgress : MutableLiveData<Boolean?>

    init {
        (application as App).getRetroComponent().inject(this)
        liveDatalist = MutableLiveData()
        showProgress = MutableLiveData()
        doAPICall()
    }

    //** return this data to update the recyclerview     **//
    fun getObserver(): MutableLiveData<Episode?>{
        return liveDatalist
    }

    fun getObserverForProgress(): MutableLiveData<Boolean?>{
        return showProgress
    }

    fun doAPICall(){
        mService.getDataFromAPI().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getDataList())
    }

    fun getDataList() : Observer<Episode> {
        return object : Observer<Episode> {
            override fun onSubscribe(d: Disposable) {
                //**start showing progress **//
                showProgress.postValue(true)
            }

            override fun onError(e: Throwable) {
                liveDatalist.postValue(null)
            }

            override fun onComplete() {
                //**hide showing progress **//
                showProgress.postValue(false)

            }

            override fun onNext(t: Episode) {
                liveDatalist.postValue(t)
            }
        }
    }
}