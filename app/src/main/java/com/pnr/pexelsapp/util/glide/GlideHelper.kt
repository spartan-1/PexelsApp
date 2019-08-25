package com.pnr.pexelsapp.util.glide

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.pnr.pexelsapp.R

/**
 * Image loading Utility
 *
 * @param view
 * @param url
 */
@BindingAdapter("bind:imageUrl")
fun loadImage(view: ImageView, url: String?) {
    GlideApp.with(view.context)
        .load(url)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .placeholder(R.drawable.glide_default)
        .into(view)
}