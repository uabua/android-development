package com.example.assignment07mariamichakhvadze

import android.graphics.Color
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.apartment_recyclerview_layout.view.*

object DataBindingComponent {
    @JvmStatic
    @BindingAdapter("src")
    fun setImage(imageView: ImageView, image: String) {
        Glide.with(imageView.context)
            .load(image)
            .into(imageView.coverImageView)
    }
}
