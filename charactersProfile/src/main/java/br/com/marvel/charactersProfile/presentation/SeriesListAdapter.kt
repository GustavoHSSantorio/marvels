package br.com.marvel.charactersProfile.presentation

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.marvel.characters.profile.R
import br.com.marvel.components.MarvelShortcutView
import br.com.marvel.network.model.MarvelSeries
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import java.lang.Exception

class SeriesListAdapter(list: List<MarvelSeries> = emptyList()) : RecyclerView.Adapter<SeriesListViewHolder>() {

    var list: List<MarvelSeries> = list
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeriesListViewHolder =
        SeriesListViewHolder(MarvelShortcutView(
            parent.context
        ).apply {
            val marginLP = ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams(parent.context.resources.getDimension(R.dimen.shortcut_width).toInt(), parent.context.resources.getDimension(R.dimen.shortcut_height).toInt()))
            marginLP.marginEnd = parent.context.resources.getDimension(R.dimen.spacing_stack_xxs).toInt()
            marginLP.marginStart = parent.context.resources.getDimension(R.dimen.spacing_stack_xs).toInt()
            marginLP.topMargin = parent.context.resources.getDimension(R.dimen.spacing_stack_xxs).toInt()
            layoutParams = marginLP
        }
    )

    override fun getItemCount(): Int =
        list.size

    override fun onBindViewHolder(holder: SeriesListViewHolder, position: Int) {
        val serie = list[position]
        Picasso.get().load(serie.thumbnail?.fullUri).resize(holder.shortcut.context.resources.getDimension(R.dimen.shortcut_width).toInt(), holder.shortcut.context.resources.getDimension(R.dimen.shortcut_height).toInt()).into(object : Target {
            override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
                holder.shortcut.imageDrawable = placeHolderDrawable
            }

            override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
                holder.shortcut.imageDrawable = errorDrawable
            }

            override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                holder.shortcut.imageBitmap = bitmap
            }
        })
    }
}

class SeriesListViewHolder(val shortcut: MarvelShortcutView) : RecyclerView.ViewHolder(shortcut)
