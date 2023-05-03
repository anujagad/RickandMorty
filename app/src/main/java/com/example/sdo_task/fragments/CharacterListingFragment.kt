package com.example.sdo_task.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sdo_task.R
import com.example.sdo_task.adapter.RvListAdapter
import com.example.sdo_task.databinding.FragmentListingBinding
import com.example.sdo_task.model.EpisodeList
import com.example.sdo_task.utils.gone
import com.example.sdo_task.utils.visible
import com.example.sdo_task.viewModel.MainActivityViewModel
import com.google.android.material.snackbar.Snackbar
import androidx.navigation.fragment.findNavController

class CharacterListingFragment : Fragment() {

    private lateinit var characterAdpater : RvListAdapter
    private lateinit var viewModel : MainActivityViewModel
    private var binding : FragmentListingBinding?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initViewModel()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentListingBinding.inflate(inflater, container, false)
        val view = binding?.root
        return view
    }

    private fun initRecyclerView(){
        // .** initialise recyclerview **//
        binding?.rvList?.layoutManager = LinearLayoutManager(requireContext())
        characterAdpater = RvListAdapter(RvListAdapter.OnClickListener { data ->
            navigateToDetailsScreen(data)
        })
        binding?.rvList?.adapter = characterAdpater
    }

    private fun navigateToDetailsScreen(data: EpisodeList?) {
        if (findNavController().currentDestination?.id == R.id.characterListingFragment) {
            val directions = CharacterListingFragmentDirections.openDetails(details = data)
            findNavController().navigate(directions)
        }
    }

    private fun initViewModel(){
        viewModel=  ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getObserver().observe(viewLifecycleOwner) { data ->
            if (data != null) {
                //** update the adapter and notify **//
                characterAdpater.setList(data.items)
                characterAdpater.notifyDataSetChanged()
            } else {
                //** Showing the snackbar to display the error message **//
                val snack = Snackbar.make(
                    View(requireContext()),
                    "Error loading data",
                    Snackbar.LENGTH_LONG
                )
                snack.show()
            }
        }

        viewModel.getObserverForProgress().observe(viewLifecycleOwner) {
            if (it == true) {
                showProgressbar()
            } else {
                hideProgressbar()
            }

        }
    }

    private fun hideProgressbar() {
        binding?.progressBar?.gone()
    }

    private fun showProgressbar() {
        binding?.progressBar?.visible()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}