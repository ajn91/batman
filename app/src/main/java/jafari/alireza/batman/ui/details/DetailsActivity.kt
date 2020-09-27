package jafari.alireza.foursquare.ui.search

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import jafari.alireza.batman.BR
import jafari.alireza.batman.R
import jafari.alireza.batman.databinding.DetailsActivityBinding
import jafari.alireza.batman.ui.base.BaseActivity
import javax.inject.Inject


class DetailsActivity : BaseActivity<DetailsActivityBinding, DetailsViewModel>() {


    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory


    override fun getBindingVariable(): Pair<Int, Any?> {
        return Pair(BR.viewModel, mViewModel)
    }

    override fun getLayoutId(): Int {
        return R.layout.details_activity
    }

    override fun setViewModel() {
        mViewModel =
            ViewModelProvider(this, viewModelFactory).get(DetailsViewModel::class.java)

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialiseView()

    }

    private fun initialiseView() {
        setSupportActionBar(viewDataBinding?.toolbar)
        setUpList()
    }

    private fun setUpList() {

    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


}
