package com.example.fitpeodemoapp.feature.ui.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.fitpeodemoapp.R
import com.example.fitpeodemoapp.databinding.ItemPhotosListBinding
import com.example.fitpeodemoapp.feature.data.model.Photos
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_photos_list.view.*

//Adapter class to display data in list
class PhotosAdapter : RecyclerView.Adapter<PhotosAdapter.PhotosViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder {
        val binding = ItemPhotosListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PhotosViewHolder(binding)
    }

    private val differCallback = object : DiffUtil.ItemCallback<Photos>() {
        override fun areItemsTheSame(
            oldItem: Photos,
            newItem: Photos
        ): Boolean {
            return oldItem == newItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(
            oldItem: Photos,
            newItem: Photos
        ): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, differCallback)
    fun photosList(list: List<Photos>) = differ.submitList(list)

    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {
        val photos = differ.currentList[position]

        //Set the data in textview
        holder.view.txtTitle.text = photos.title
        holder.view.txtURL.text = photos.thumbnailUrl

        //Image load in imageview
        Picasso.get()
            .load(photos.thumbnailUrl)
            .placeholder(R.mipmap.ic_launcher)
            .error(R.mipmap.ic_launcher)
            .into(holder.view.imgPhotos)
    }

    override fun getItemCount(): Int {
        return  differ.currentList.size
    }

    class PhotosViewHolder (val view : ItemPhotosListBinding): RecyclerView.ViewHolder(view.root)
}