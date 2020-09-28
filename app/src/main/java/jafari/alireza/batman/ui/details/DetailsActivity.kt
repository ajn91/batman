package jafari.alireza.foursquare.ui.search

import android.graphics.PorterDuff
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.lifecycle.ViewModelProvider
import com.devs.readmoreoption.ReadMoreOption
import com.google.android.material.appbar.AppBarLayout
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
                    getExpandableOption()
                        .addReadMoreTo(viewDataBinding?.txtPlot, it.plot)
                    viewDataBinding?.rtb?.rating = it.imdbRating.toFloat()

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

    private fun getExpandableOption() =
        ReadMoreOption.Builder(this)
            .textLength(2, ReadMoreOption.TYPE_LINE)
            .moreLabelColor(ContextCompat.getColor(this, R.color.colorAccent))
            .lessLabelColor(ContextCompat.getColor(this, R.color.colorAccent))
            .labelUnderLine(true)
            .expandAnimation(true)
            .build()


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
        setUpAppBar()

    }

    private fun setUpAppBar() {
        viewDataBinding?.appbar?.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            if (viewDataBinding?.collapsingToolbar!!.getHeight() + verticalOffset < 2 * ViewCompat.getMinimumHeight(
                    viewDataBinding?.collapsingToolbar!!
                )
            ) {
                viewDataBinding?.toolbar?.getNavigationIcon()
                    ?.setColorFilter(
                        resources.getColor(R.color.textTitleColor),
                        PorterDuff.Mode.SRC_ATOP
                    )
            } else {
                viewDataBinding?.toolbar?.getNavigationIcon()
                    ?.setColorFilter(
                        resources.getColor(R.color.textColorToolbarExpanded),
                        PorterDuff.Mode.SRC_ATOP
                    )
            }
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {
        const val EXTRA_ID_NAME = "id"
    }
}
