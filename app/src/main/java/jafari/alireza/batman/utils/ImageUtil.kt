package jafari.alireza.batman.utils

import android.content.Context
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide


object ImageUtil {

    fun showImage(
        context: Context?,
        url: String?,
        imageView: ImageView,
        @DrawableRes placeHolder: Int? = null
    ) {
        if (context != null) {
            Glide.with(context)
                .load(url)
//                .placeholder(placeHolder)
                .dontAnimate()
//                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView)
        }
    }


}
