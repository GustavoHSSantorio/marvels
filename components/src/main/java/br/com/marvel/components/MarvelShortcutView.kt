package br.com.marvel.components

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.DrawableRes
import androidx.cardview.widget.CardView
import kotlinx.android.synthetic.main.shortcut_image.view.*

class MarvelShortcutView(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.shortcut_image, this)

        populate(attrs)
    }

    private fun populate(attrs: AttributeSet?) {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.Shortcut,
            0, 0).apply {

            imageResource = getResourceId(R.styleable.Shortcut_imageSrc, 0)
        }
    }

    @DrawableRes
    var imageResource: Int = 0
        set(value) {
            field = value
            card_image.setImageResource(value)
        }

    var imageDrawable: Drawable? = null
        set(value) {
            field = value
            card_image.setImageDrawable(value)
        }

    var imageBitmap: Bitmap? = null
        set(value) {
            field = value
            card_image.setImageBitmap(value)
        }
}
