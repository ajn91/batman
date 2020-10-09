package  jafari.alireza.batman.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


open class BaseViewModel : ViewModel() {

    val _messageStringLive = MutableLiveData<String>()
    val messageStringLive: LiveData<String>
        get() = _messageStringLive
    val _messageIdLive = MutableLiveData<Int>()
    val messageIdLive: LiveData<Int>
        get() = _messageIdLive


    open fun onStop() {
    }



}
