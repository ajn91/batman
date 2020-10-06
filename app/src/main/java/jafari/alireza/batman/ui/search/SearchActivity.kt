package jafari.alireza.batman.ui.search

import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import jafari.alireza.batman.BR
import jafari.alireza.batman.R
import jafari.alireza.batman.data.domain.search.SearchModel
import jafari.alireza.batman.data.source.remote.ResponseStatus
import jafari.alireza.batman.databinding.SearchActivityBinding
import jafari.alireza.batman.ui.appinterface.OnItemClickListener
import jafari.alireza.batman.ui.base.BaseActivity
import jafari.alireza.batman.ui.details.DetailsActivity
import jafari.alireza.batman.utils.DetailsParams
import jafari.alireza.batman.utils.DirectionParamName
import jafari.alireza.batman.utils.NavigationUtil
import javax.inject.Inject

@AndroidEntryPoint
class SearchActivity : BaseActivity<SearchActivityBinding, SearchViewModel>(),
    OnItemClickListener<SearchModel> {
    override val mViewModel: SearchViewModel by viewModels()


    @Inject
    lateinit var searchAdapter: SearchAdapter


    override fun getBindingVariable(): Pair<Int, Any?> {
        return Pair(BR.viewModel, mViewModel)
    }

    override fun getLayoutId(): Int {
        return R.layout.search_activity
    }

    override fun createViewModelObserver() {

//        mViewModel =
//            ViewModelProvider(this, viewModelFactory).get(SearchViewModel::class.java)

        mViewModel.itemsLive.observe(this, ::handleItems)
        mViewModel.directToPageLive.observe(this, ::directToPage)
        mViewModel.messageStringLive.observe(this, ::handleMessage)
        mViewModel.messageIdLive.observe(this, ::handleMessageResource)

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
            mViewModel.onPageChanged()
        }
    }

    private fun goToDetailsPage(id: String) {
        val bundle = Bundle()
        bundle.putString(DetailsParams.ID_Name, id)
        NavigationUtil.startActivity(this, DetailsActivity::class.java, bundle)

    }

    private fun handleItems(items: Pair<ResponseStatus, List<SearchModel>?>) {
        val status = items.first
        when (status) {
            is ResponseStatus.SUCCESS -> {
                if (items.second != null)
                    searchAdapter.setItems(items.second!!)
            }
            is ResponseStatus.LOADING -> {
                viewDataBinding?.txtListStatus?.text = getString(R.string.loading)
            }
            is ResponseStatus.ERROR -> {
                viewDataBinding?.txtListStatus?.text = status.message
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


    override fun onItemClick(item: SearchModel) {
        mViewModel.onItemClick(item)
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


}
