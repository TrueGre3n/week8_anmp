package com.ubaya.studentapp.util

import android.widget.ImageView
import android.widget.ProgressBar
import com.squareup.picasso.Picasso
import com.ubaya.studentapp.R

fun ImageView.loadImage(url: String?, progressBar: ProgressBar) {
    Picasso.get()
        .load(url)
        .resize(400, 400)
        .centerCrop()
        .error(R.drawable.ic_launcher_background)
        .into(this)

}
