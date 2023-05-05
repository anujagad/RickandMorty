package com.example.sdo_task.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sdo_task.di.RetroServiceinterface
import com.example.sdo_task.model.Episode
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(private val mService : RetroServiceinterface): ViewModel() {


    private lateinit var liveDatalist : MutableLiveData<Episode?>
    private lateinit var showProgress : MutableLiveData<Boolean?>

    init {
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