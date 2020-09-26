package jafari.alireza.batman.utils

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import jafari.alireza.batman.R


object UiUtils {
    fun createDivider(context: Context): DividerItemDecoration {
        val decoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        decoration.setDrawable(
            ContextCompat.getDrawable(
                context,
                R.color.dividerColor
            )!!
        )
        return decoration
    }


}