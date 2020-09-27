package jafari.alireza.foursquare.ui.search

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import jafari.alireza.batman.BR
import jafari.alireza.batman.R
import jafari.alireza.batman.data.domain.details.DetailsModel
import jafari.alireza.batman.data.source.remote.Resource
import jafari.alireza.batman.data.source.remote.ResourceStatus
import jafari.alireza.batman.databinding.DetailsActivityBinding
import jafari.alireza.batman.ui.base.BaseActivity
import jafari.alireza.batman.utils.ImageUtil
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
        mViewModel?.detailsResourceLive?.observe(this, ::handleDetails)

    }

    private fun handleDetails(details: Resource<DetailsModel>?) {
        when (details?.status) {
            ResourceStatus.SUCCESS -> {
                viewDataBinding?.collapsingToolbar?.setTitleEnabled(true);

                details.data?.let {

                    ImageUtil.showImage(
                        this,
                        it.poster, viewDataBinding?.imgBackground!!
                    )
                    ImageUtil.showImage(
                        this,
                        it.poster, viewDataBinding?.imgPoster!!
                    )
                    supportActionBar?.title = it.title
                    viewDataBinding?.collapsingToolbar?.title = it.title
                }

            }
            ResourceStatus.LOADING -> {
                viewDataBinding?.txtListStatus?.text = getString(R.string.loading)
            }
            ResourceStatus.ERROR -> {
//                viewDataBinding?.appbar?.setExpanded(false)
                viewDataBinding?.collapsingToolbar?.setTitleEnabled(false)

                viewDataBinding?.txtListStatus?.text = details.message
            }
            ResourceStatus.LOADED -> {
            }
            null -> {
            }
        }


    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val extra = intent.extras
        if (extra != null && extra.containsKey(EXTRA_ID_NAME)) {
            val id = extra.getString(EXTRA_ID_NAME)
            mViewModel?.getDetails(id!!)
        }
        initialiseView()

    }

    private fun initialiseView() {
        setSupportActionBar(viewDataBinding?.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {
        const val EXTRA_ID_NAME = "id"
    }
}
