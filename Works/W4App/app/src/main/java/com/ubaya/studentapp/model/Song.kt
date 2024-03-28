package com.ubaya.studentapp.model

import java.util.ArrayList

data class Song(
    var id:String?,
    var title:String?,
    var artist:String?,
    var genre:ArrayList<String>,
    var album:Album,
    var images:String?
)
