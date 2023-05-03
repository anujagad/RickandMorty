package com.example.sdo_task.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Episode (
    @SerializedName("info")
    val info : Info,
    @SerializedName("results")
    val items:List<EpisodeList>) : Serializable