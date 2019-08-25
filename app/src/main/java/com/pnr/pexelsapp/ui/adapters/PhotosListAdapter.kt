package com.pnr.pexelsapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.pnr.pexelsapp.BR
import com.pnr.pexelsapp.R
import com.pnr.pexelsapp.model.PexelPhoto

class PhotosListAdapter(
    private val pexelPhotos: ArrayList<PexelPhoto>,
    private var listener: OnItemClickListener
) :
    RecyclerView.Adapter<PhotosListAdapter.PhotosViewHolder>() {

    /**
     * PhotosViewHolder ViewHolder implementation
     *
     * @property viewBinding
     */
    class PhotosViewHolder(private val viewBinding: ViewDataBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(data: Any, listener: OnItemClickListener) {
            viewBinding.setVariable(BR.pexelPhoto, data)
            (viewBinding.root.findViewById(R.id.image_pexel) as ImageView).setOnClickListener { _ ->
                listener.onThumbnailClick(data as PexelPhoto)
            }
            viewBinding.executePendingBindings()
        }
    }

    interface OnItemClickListener {
        fun onThumbnailClick(pexelPhoto: PexelPhoto)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder {
        val layourInflator = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<ViewDataBinding>(
                layourInflator,
                R.layout.item_pexel_photo,
                parent,
                false
            )

        return PhotosViewHolder(binding)
    }

    override fun onBindViewHolder(photosViewHolder: PhotosViewHolder, position: Int) {
        photosViewHolder.bind(pexelPhotos[position], listener)
    }

    override fun getItemCount(): Int {
        return pexelPhotos.size
    }

    /**
     * function to update Pexel Photos
     *
     * @param pexelPhotos
     */
    fun updatePhotos(pexelPhotos: List<PexelPhoto>) {
        this.pexelPhotos.addAll(pexelPhotos)
        notifyDataSetChanged()
    }

    /**
     * function to clear Pexel Photos
     *
     */
    fun clearPhotos() {
        this.pexelPhotos.clear()
        notifyDataSetChanged()
    }
}