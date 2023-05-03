package com.example.sdo_task.model

import java.io.Serializable

data class EpisodeList (
    val id : Int,
    val name : String?=null,
    val status :String?=null,
    val species :String?=null,
    val gender :String?=null,
    val image :String?=null) : Serializable