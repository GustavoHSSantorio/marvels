package br.com.marvel.charactersProfile.presentation

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

object BindingAdapter {

    @BindingAdapter("loadImage")
    @JvmStatic
    fun setVisible(view: AppCompatImageView, imageUri: String?) {
        Picasso.get().load(imageUri).fit().centerCrop().into(view)
    }
}
