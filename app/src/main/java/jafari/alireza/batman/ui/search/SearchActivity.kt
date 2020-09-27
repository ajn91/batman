package jafari.alireza.foursquare.ui.search

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import jafari.alireza.batman.BR
import jafari.alireza.batman.R
import jafari.alireza.batman.data.domain.search.SearchModel
import jafari.alireza.batman.databinding.SearchActivityBinding
import jafari.alireza.batman.ui.appinterface.AdapterInterface
import jafari.alireza.batman.ui.base.BaseActivity
import javax.inject.Inject


class SearchActivity : BaseActivity<SearchActivityBinding, SearchViewModel>(),
    AdapterInterface.OnItemClickListener {


    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var searchAdapter: SearchAdapter


    override fun getBindingVariable(): Pair<Int, Any?> {
        return Pair(BR.viewModel, mViewModel)
    }

    override fun getLayoutId(): Int {
        return R.layout.search_activity
    }

    override fun setViewModel() {
        mViewModel =
            ViewModelProvider(this, viewModelFactory).get(SearchViewModel::class.java)
        mViewModel?.itemsLive?.observe(this, ::fillList)

    }

    private fun fillList(list: List<SearchModel>?) {
        if (list != null)
            searchAdapter.setItems(list)

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
        val spanCount = resources.getInteger(R.integer.search_list_span_count)
        val layoutManager = GridLayoutManager(this, spanCount)

        viewDataBinding?.rcv?.apply {
            this.layoutManager = layoutManager
            adapter = searchAdapter
//            addItemDecoration(UiUtils.createDivider(this@SearchActivity))

        }
        searchAdapter.onItemClickListener = this

    }


    override fun onItemClick(position: Int) {

//            mViewModel?.onItemClick(position)
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


}
