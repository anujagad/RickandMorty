package com.example.sdo_task.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sdo_task.adapter.RvListAdapter
import com.example.sdo_task.databinding.ActivityMainBinding
import com.example.sdo_task.model.Episode
import com.example.sdo_task.model.EpisodeList
import com.example.sdo_task.utils.gone
import com.example.sdo_task.utils.visible
import com.example.sdo_task.viewModel.MainActivityViewModel
import com.google.android.material.snackbar.Snackbar
import java.io.Serializable

class MainActivity : AppCompatActivity() {

    private lateinit var characterAdpater : RvListAdapter
    private lateinit var viewModel : MainActivityViewModel
    private var binding : ActivityMainBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view  = binding?.root
        setContentView(view)
        initRecyclerView()
        initViewModel()
    }

    private fun initRecyclerView(){
        // .** initialise recyclerview **//
        binding?.rvList?.layoutManager = LinearLayoutManager(this)
        characterAdpater = RvListAdapter(RvListAdapter.OnClickListener { data ->
            navigateToDetailsScreen(data)
        })
        binding?.rvList?.adapter = characterAdpater
    }

    private fun navigateToDetailsScreen(data: EpisodeList?) {
        val intent = Intent(this,DetailsActivity::class.java)
        intent.putExtra("characterDetails",data as Serializable)
        startActivity(intent)
    }

    private fun initViewModel(){
        viewModel=  ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getObserver().observe(this,Observer<Episode?>{data ->
                if(data !=null){
                    //** update the adapter and notify **//
                    characterAdpater.setList(data.items)
                    characterAdpater.notifyDataSetChanged()
                }else{
                    //** Showing the snackbar to display the error message **//
                    val snack = Snackbar.make(View(this@MainActivity),"Error loading data",Snackbar.LENGTH_LONG)
                    snack.show()
                }
        })

        viewModel.getObserverForProgress().observe(this,Observer<Boolean?>{
            if(it == true){
                showProgressbar()
            }else{
                hideProgressbar()
            }

        })
        viewModel.doAPICall()
    }

    private fun hideProgressbar() {
        binding?.progressBar?.gone()
    }

    private fun showProgressbar() {
        binding?.progressBar?.visible()

    }
}

