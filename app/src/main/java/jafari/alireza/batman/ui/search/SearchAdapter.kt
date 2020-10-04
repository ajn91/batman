package jafari.alireza.batman.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.scopes.ActivityScoped
import jafari.alireza.batman.data.domain.search.SearchModel
import jafari.alireza.batman.databinding.SearchItemLayoutBinding
import jafari.alireza.batman.ui.appinterface.AdapterInterface
import jafari.alireza.batman.utils.ImageUtil
import javax.inject.Inject

@ActivityScoped
class SearchAdapter @Inject constructor() :
    RecyclerView.Adapter<SearchAdapter.ItemViewHolder>() {
    var onItemClickListener: AdapterInterface.OnItemClickListener? = null

    private var mItems = emptyList<SearchModel>()
    fun setItems(items: List<SearchModel>) {
        val diffCallback = DiffCallback(mItems, items)
        val diffResult = DiffUtil.calculateDiff(diffCallback, true)
        mItems = items
        diffResult.dispatchUpdatesTo(this)

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ItemViewHolder(
            SearchItemLayoutBinding.inflate(inflater, parent, false)
        )
    }


    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = mItems.get(position)
        holder.binding.item = item
        ImageUtil.showImage(
            holder.itemView.context,
            item.poster,
            holder.binding.imgCategoryIcon
        )
        holder.itemView.setOnClickListener {
            onItemClickListener?.onItemClick(position)
        }

    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    class ItemViewHolder(var binding: SearchItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)


    inner class DiffCallback(
        private val mOldList: List<SearchModel>,
        private val mNewList: List<SearchModel>
    ) : DiffUtil.Callback() {

        override fun getOldListSize(): Int {
            return mOldList.size
        }

        override fun getNewListSize(): Int {
            return mNewList.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return mOldList[oldItemPosition].imdbID == mNewList[newItemPosition].imdbID
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldItem = mOldList[oldItemPosition]
            val newItem = mNewList[newItemPosition]

            return (newItem.equals(oldItem))
        }

    }
}
