package jafari.alireza.batman.utils

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.core.app.ActivityCompat


object NavigationUtil {


    fun startActivity(
        currentActivity: Activity,
        destinationActivityClass: Class<*>,
        bundle: Bundle?,
        keepCurrentActivity: Boolean = true
    ) {
        val intent = Intent(currentActivity, destinationActivityClass)
        bundle?.let { intent.putExtras(it) }
        ActivityCompat.startActivity(currentActivity, intent, null)
        if (!keepCurrentActivity)
            currentActivity.finish()

    }


}

