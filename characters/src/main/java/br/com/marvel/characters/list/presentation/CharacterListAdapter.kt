package br.com.marvel.characters.list.presentation

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.marvel.characters.R
import br.com.marvel.components.MarvelCardView
import br.com.marvel.network.model.MarvelCharacter
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import java.lang.Exception

class CharacterListAdapter(list: List<MarvelCharacter> = emptyList()) : RecyclerView.Adapter<CharacterListViewHolder>() {

    var list: List<MarvelCharacter> = list
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterListViewHolder =
        CharacterListViewHolder(
            MarvelCardView(
                parent.context
            ).apply {
                val marginLP = ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT))
                marginLP.marginEnd = parent.context.resources.getDimension(R.dimen.spacing_stack_xxs).toInt()
                marginLP.marginStart = parent.context.resources.getDimension(R.dimen.spacing_stack_xs).toInt()
                marginLP.topMargin = parent.context.resources.getDimension(R.dimen.spacing_stack_xxs).toInt()
                layoutParams = marginLP
            }
        )

    override fun getItemCount(): Int =
        list.size

    override fun onBindViewHolder(holder: CharacterListViewHolder, position: Int) {
        val character = list[position]

        holder.card.title = character.name
        holder.card.content = character.description

        Picasso.get().load(character.thumbnail?.fullUri).into(object : Target {
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

class CharacterListViewHolder(val card: MarvelCardView) : RecyclerView.ViewHolder(card)
