package com.example.sdo_task.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.sdo_task.R
import com.example.sdo_task.databinding.ActivityDetailsBinding
import com.example.sdo_task.model.EpisodeList

class DetailsActivity : AppCompatActivity() {

    private var binding : ActivityDetailsBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        val view  = binding?.root
        val details : EpisodeList = intent?.getSerializableExtra("characterDetails") as EpisodeList

        setContentView(view)
        initView(details)
        binding?.toolbar?.setOnClickListener {
           super.onBackPressed()
        }
    }
    private fun initView(details: EpisodeList) {
        binding?.toolbar?.title = details.name
        binding?.tvName?.text = getString(R.string.name, details.name)
        binding?.tvGender?.text = getString(R.string.gender, details.gender)
        binding?.tvSpecies?.text = getString(R.string.species, details.species)
        binding?.tvStatus?.text = getString(R.string.status, details.status)

        binding?.ivCharacter?.let {
            Glide.with(applicationContext)
                .load(details.image)
                .placeholder(R.drawable.ic_placeholder_app_inbox_circle_image)
                .into(it)
        }
    }
}