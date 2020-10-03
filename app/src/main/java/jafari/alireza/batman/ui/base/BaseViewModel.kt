package  jafari.alireza.batman.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


open class BaseViewModel : ViewModel() {
    val compositeDisposable: CompositeDisposable = CompositeDisposable()

    val _messageStringLive = MutableLiveData<String>()
    val messageStringLive: LiveData<String>
        get() = _messageStringLive
    val _messageIdLive = MutableLiveData<Int>()
    val messageIdLive: LiveData<Int>
        get() = _messageIdLive

    protected fun addToDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    protected fun isDisposableEmpty() = compositeDisposable.size() == 0
    open fun onStop() {
        if (!compositeDisposable.isDisposed)
            compositeDisposable.dispose()
        compositeDisposable.clear()
    }



}
