package br.com.marvel.comics.presentation

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.marvel.comics.R
import br.com.marvel.components.MarvelCardView
import br.com.marvel.network.model.MarvelComic
import com.squareup.picasso.Picasso

class ComicListAdapter(list: List<MarvelComic> = emptyList()) : RecyclerView.Adapter<ComicListViewHolder>() {

    var list: List<MarvelComic> = list
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicListViewHolder =
        ComicListViewHolder(
            MarvelCardView(
                parent.context
            ).apply {
                val marginLP = ViewGroup.MarginLayoutParams(
                    ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )
                )
                marginLP.marginEnd =
                    parent.context.resources.getDimension(R.dimen.spacing_stack_xxs).toInt()
                marginLP.marginStart =
                    parent.context.resources.getDimension(R.dimen.spacing_stack_xs).toInt()
                marginLP.topMargin =
                    parent.context.resources.getDimension(R.dimen.spacing_stack_xxs).toInt()
                layoutParams = marginLP
            }
        )

    override fun getItemCount(): Int =
        list.size

    override fun onBindViewHolder(holder: ComicListViewHolder, position: Int) {
        val comic = list[position]

        holder.card.title = comic.title
        holder.card.content = comic.description

        Picasso.get().load(comic.thumbnail?.fullUri).into(object : com.squareup.picasso.Target {
            override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
                holder.card.imageDrawable = placeHolderDrawable
            }

            override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
                holder.card.imageDrawable = errorDrawable
            }

            override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                holder.card.imageBitmap = bitmap
            }
        })
    }
}

class ComicListViewHolder(val card: MarvelCardView) : RecyclerView.ViewHolder(card)
