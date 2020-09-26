package jafari.alireza.batman.utils

import android.content.Context
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import jafari.alireza.batman.R


object ImageUtil {

    fun showImage(
        context: Context?,
        url: String?,
        imageView: ImageView,
        @DrawableRes placeHolder: Int = R.drawable.batman
    ) {
        if (context != null) {
            Glide.with(context)
                .load(url)
                .transform(CenterInside(), RoundedCorners(16))
                .placeholder(placeHolder)
                .error(placeHolder)
                .dontAnimate()
//                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView)
        }
    }


}
