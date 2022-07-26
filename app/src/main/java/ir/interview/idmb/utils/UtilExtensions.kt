package ir.interview.idmb.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy


inline fun loadImage(img: ImageView, url: String) {
    Glide.with(img.context)
        .load(url)
        .centerCrop()
        .diskCacheStrategy(DiskCacheStrategy.DATA)
        .into(img)
}