package jafari.alireza.foursquare.ui.search

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import jafari.alireza.batman.BR
import jafari.alireza.batman.R
import jafari.alireza.batman.data.domain.search.SearchModel
import jafari.alireza.batman.data.source.remote.Resource
import jafari.alireza.batman.data.source.remote.ResourceStatus
import jafari.alireza.batman.databinding.SearchActivityBinding
import jafari.alireza.batman.ui.appinterface.AdapterInterface
import jafari.alireza.batman.ui.base.BaseActivity
import jafari.alireza.batman.utils.DirectionParamName
import jafari.alireza.batman.utils.NavigationUtil
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
        mViewModel?.itemsLive?.observe(this, ::handleItems)
        mViewModel?.directToPageLive?.observe(this, ::directToPage)
        mViewModel?.messageStringLive?.observe(this, ::handleMessage)
        mViewModel?.messageIdLive?.observe(this, ::handleMessageResource)

    }

    private fun handleMessageResource(id: Int?) {
        id?.let {
            viewDataBinding?.txtListStatus?.text = getString(it)
        }

    }

    private fun handleMessage(message: String?) {
        viewDataBinding?.txtListStatus?.text = message

    }

    private fun directToPage(directionParamName: DirectionParamName?) {
        directionParamName?.let {
            when (directionParamName) {
                is DirectionParamName.DetailsParams -> goToDetailsPage(directionParamName.id)
            }
            mViewModel?.directToPageLive?.value = null
        }
    }

    private fun goToDetailsPage(id: String) {
        val bundle = Bundle()
        bundle.putString(DetailsActivity.EXTRA_ID_NAME, id)
        NavigationUtil.startActivity(this, DetailsActivity::class.java, bundle)

    }

    private fun handleItems(items: Resource<List<SearchModel>>?) {
        Log.d("LOG", "handleItems: ${items?.status}")

        when (items?.status) {
            ResourceStatus.SUCCESS -> {
                if (items.data != null)
                    searchAdapter.setItems(items.data)
            }
            ResourceStatus.LOADING -> {
                viewDataBinding?.txtListStatus?.text = getString(R.string.loading)
            }
            ResourceStatus.ERROR -> {
                viewDataBinding?.txtListStatus?.text = items.message
            }
            ResourceStatus.LOADED -> {
            }
            null -> {
            }
        }
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

        mViewModel?.onItemClick(position)
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


}
