package com.example.android.futureworks.utils

import android.graphics.drawable.PictureDrawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android.futureworks.R
import com.example.android.futureworks.adapter.ArticleAdapter
import com.example.android.futureworks.models.Article

@BindingAdapter("imageUrl")
fun bindThumbnail(imageView: ImageView, imageUrl: String?) {
    imageUrl?.let {
        Glide.with(imageView.context)
            //.`as`(PictureDrawable::class.java)
            .load(imageUrl)
            .centerCrop()
            //.override(10, 10)
            .placeholder(R.drawable.ic_launcher_background)
            //.listener(SvgSoftwareLayerSetter())
            .into(imageView)
    }
}