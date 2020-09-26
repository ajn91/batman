package  jafari.alireza.batman.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


open class BaseViewModel : ViewModel() {
    val compositeDisposable: CompositeDisposable = CompositeDisposable()

    val messageStringLive = MutableLiveData<String>()
    val messageIdLive = MutableLiveData<Int>()
    protected fun addToDisposable(disposable: Disposable) {
        compositeDisposable.remove(disposable)
        compositeDisposable.add(disposable)
    }

    protected fun isDisposableEmpty() = compositeDisposable.size() == 0
    open fun onStop() {
        if (!compositeDisposable.isDisposed)
            compositeDisposable.dispose()
        compositeDisposable.clear()
    }


    class Success(val title: String, val description: String)

}
