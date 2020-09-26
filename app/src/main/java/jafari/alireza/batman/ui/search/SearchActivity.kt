package jafari.alireza.foursquare.ui.search

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import jafari.alireza.batman.BR
import jafari.alireza.batman.R
import jafari.alireza.batman.databinding.SearchActivityBinding
import jafari.alireza.batman.ui.appinterface.AdapterInterface
import jafari.alireza.batman.ui.base.BaseActivity
import jafari.alireza.batman.utils.UiUtils

import javax.inject.Inject


class SearchActivity : BaseActivity<SearchActivityBinding, SearchViewModel>(),
    AdapterInterface.OnItemClickListener {


    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var exploreAdapter: ExploreAdapter


    override fun getBindingVariable(): Pair<Int, Any?> {
        return Pair(BR.viewModel, mViewModel)
    }

    override fun getLayoutId(): Int {
        return R.layout.search_activity
    }

    override fun setViewModel() {
        mViewModel =
            ViewModelProvider(this, viewModelFactory).get(SearchViewModel::class.java)


    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialiseView()

    }

    private fun initialiseView() {
        setSupportActionBar(viewDataBinding?.toolbar?.toolbar!!)
        setUpList()
    }

    private fun setUpList() {
        val layoutManager = LinearLayoutManager(this)

        viewDataBinding?.rcv?.apply {
            this.layoutManager = layoutManager
            adapter = exploreAdapter
            addItemDecoration(UiUtils.createDivider(this@SearchActivity))

        }
        exploreAdapter.onItemClickListener = this

    }


    override fun onItemClick(position: Int) {

//            mViewModel?.onItemClick(position)
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


}
