package ir.interview.idmb.utils

import android.graphics.drawable.ColorDrawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy


inline fun loadImage(img: ImageView, url: String) {
    Glide.with(img.context)
        .load(url)
        .centerCrop()
        .placeholder(ColorDrawable(121212))
        .diskCacheStrategy(DiskCacheStrategy.DATA)
        .into(img)
}