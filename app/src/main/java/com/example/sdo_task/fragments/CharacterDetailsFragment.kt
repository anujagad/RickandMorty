package com.example.sdo_task.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.sdo_task.R
import com.example.sdo_task.databinding.FragmentDetailsBinding
import com.example.sdo_task.model.EpisodeList

class CharacterDetailsFragment : Fragment(){
    private var binding : FragmentDetailsBinding?=null
    private val args: CharacterDetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        args.details?.let { initView(it) }

        binding?.toolbar?.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        val view = binding?.root
        return view
    }

    private fun initView(details: EpisodeList) {
        binding?.toolbar?.title = details.name
        binding?.tvName?.text = getString(R.string.name, details.name)
        binding?.tvGender?.text = getString(R.string.gender, details.gender)
        binding?.tvSpecies?.text = getString(R.string.species, details.species)
        binding?.tvStatus?.text = getString(R.string.status, details.status)

        binding?.ivCharacter?.let {
            Glide.with(requireContext())
                .load(details.image)
                .placeholder(R.drawable.ic_placeholder_app_inbox_circle_image)
                .into(it)
        }
    }
}