package jafari.alireza.batman.ui.base

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import jafari.alireza.batman.utils.ImageUtil


@BindingAdapter("imageUrl")
fun setImageUri(view: ImageView, imageUrl: String?) {
    ImageUtil.showImage(view.context, imageUrl, view)

}

@BindingAdapter("android:text")
fun setFloat(view: TextView, value: Float) {
    if (value.isNaN())
        view.setText("")
    else
        view.setText(value.toString())
}

@InverseBindingAdapter(attribute = "android:text")
fun getFloat(view: TextView): Float {
    val num = view.getText().toString()
    if (num.isEmpty())
        return 0.0f
    try {
        return num.toFloat()
    } catch (e: NumberFormatException) {
        return 0.0f
    }
}

//    @BindingAdapter("imageResource")
//    fun setImageUri(view: ImageView, imageUri: String?) {
//        if (imageUri == null) {
//            view.setImageURI(null)
//        } else {
//            view.setImageURI(Uri.parse(imageUri))
//        }
//    }
//
//    @BindingAdapter("imageResource")
//    fun setImageUri(view: ImageView, imageUri: Uri?) {
//        view.setImageURI(imageUri)
//    }
//
//    @BindingAdapter("imageResource")
//    fun setImageDrawable(view: ImageView, drawable: Drawable?) {
//        view.setImageDrawable(drawable)
//    }
//
//    @BindingAdapter("imageResource")
//    fun setImageResource(imageView: ImageView, resource: Int) {
//        imageView.setImageResource(resource)
//    }

//    @BindingAdapter("setBold")
//    fun setBold(view: TextView, isBold: Boolean) {
//        if (isBold) {
//            view.setTypeface(null, Typeface.BOLD)
//        } else {
//            view.setTypeface(null, Typeface.NORMAL)
//        }
//    }
