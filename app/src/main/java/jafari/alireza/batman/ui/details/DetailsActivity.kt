package jafari.alireza.batman.ui.details

import android.graphics.PorterDuff
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import com.devs.readmoreoption.ReadMoreOption
import com.google.android.material.appbar.AppBarLayout
import dagger.hilt.android.AndroidEntryPoint
import jafari.alireza.batman.BR
import jafari.alireza.batman.R
import jafari.alireza.batman.data.domain.details.DetailsModel
import jafari.alireza.batman.data.source.remote.ResponseStatus
import jafari.alireza.batman.databinding.DetailsActivityBinding
import jafari.alireza.batman.ui.base.BaseActivity
import jafari.alireza.batman.utils.ImageUtil

@AndroidEntryPoint
class DetailsActivity : BaseActivity<DetailsActivityBinding, DetailsViewModel>() {




    override fun getBindingVariable(): Pair<Int, Any?> {
        return Pair(BR.viewModel, mViewModel)
    }

    override fun getLayoutId(): Int {
        return R.layout.details_activity
    }

    override fun setViewModel() {
//        mViewModel =
//            ViewModelProvider(this, viewModelFactory).get(DetailsViewModel::class.java)
        val viewModel: DetailsViewModel by viewModels()
        mViewModel = viewModel
        mViewModel?.detailsResourceLive?.observe(this, ::handleDetails)
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

    private fun handleDetails(details: Pair<ResponseStatus, DetailsModel?>) {
        val status = details.first
        when (status) {
            is ResponseStatus.SUCCESS -> {
                viewDataBinding?.collapsingToolbar?.setTitleEnabled(true);

                details.second.let {

                    ImageUtil.showImage(
                        this,
                        it?.poster, viewDataBinding?.imgBackground!!
                    )
                    ImageUtil.showImage(
                        this,
                        it?.poster, viewDataBinding?.imgPoster!!
                    )
                    supportActionBar?.title = it?.title
                    viewDataBinding?.collapsingToolbar?.title = it?.title
                    getExpandableOption()
                        .addReadMoreTo(viewDataBinding?.txtPlot, it?.plot)
                    viewDataBinding?.rtb?.rating = it?.imdbRating?.toFloat() ?: 0f

                }

            }
            is ResponseStatus.LOADING -> {
                viewDataBinding?.txtListStatus?.text = getString(R.string.loading)
            }
            is ResponseStatus.ERROR -> {
//                viewDataBinding?.appbar?.setExpanded(false)
                viewDataBinding?.collapsingToolbar?.setTitleEnabled(false)
                viewDataBinding?.txtListStatus?.text = status.message
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
        viewDataBinding?.txtActors?.setOnClickListener {
            mViewModel?.getDetails(mViewModel?.id!!)
        }
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
