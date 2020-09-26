package jafari.alireza.batman.ui.base


import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import dagger.android.AndroidInjection


abstract class BaseActivity<T : ViewDataBinding, V : BaseViewModel> : AppCompatActivity() {

    var viewDataBinding: T? = null
        private set
    var mViewModel: V? = null

    /**
     * Override for set binding variable
     *
     * @return variable id
     */
    abstract fun getBindingVariable(): Pair<Int, Any?>

    /**
     * @return layout resource id
     */
    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun setViewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        performDependencyInjection()
        super.onCreate(savedInstanceState)
        performDataBinding()
    }


    fun performDependencyInjection() {
        AndroidInjection.inject(this)
    }

    private fun performDataBinding() {
        viewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        if (mViewModel == null)
            setViewModel()
        viewDataBinding!!.setVariable(getBindingVariable().first, getBindingVariable().second)
        viewDataBinding!!.lifecycleOwner = this
        viewDataBinding!!.executePendingBindings()

    }

    override fun onDestroy() {
        mViewModel?.onStop()
        super.onDestroy()
    }

}

