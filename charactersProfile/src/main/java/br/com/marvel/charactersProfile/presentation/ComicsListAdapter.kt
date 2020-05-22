package br.com.marvel.charactersProfile.presentation

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import br.com.marvel.characters.profile.R
import br.com.marvel.components.CircleTransform
import br.com.marvel.network.model.MarvelComic
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import java.lang.Exception

class ComicsListAdapter(list : List<MarvelComic> = emptyList()): RecyclerView.Adapter<ComicListViewHolder>(){

    var list : List<MarvelComic> = list
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicListViewHolder =
        ComicListViewHolder(ImageView(
            parent.context
        ).apply {
            val marginLP = ViewGroup.MarginLayoutParams(
                ViewGroup.LayoutParams(parent.context.resources.getDimension(
                    R.dimen.image_size).toInt(), parent.context.resources.getDimension(R.dimen.image_size).toInt()))
            marginLP.marginEnd = parent.context.resources.getDimension(R.dimen.spacing_stack_xxs).toInt()
            marginLP.marginStart = parent.context.resources.getDimension(R.dimen.spacing_stack_xs).toInt()
            marginLP.topMargin = parent.context.resources.getDimension(R.dimen.spacing_stack_xxs).toInt()
            layoutParams = marginLP
        }
        )

    override fun getItemCount(): Int =
        list.size

    override fun onBindViewHolder(holder: ComicListViewHolder, position: Int) {
        val comic = list[position]
        Picasso.get().load(comic.thumbnail?.fullUri).transform(CircleTransform()).into(holder.image)
    }
}

class ComicListViewHolder(val image : ImageView) : RecyclerView.ViewHolder(image)