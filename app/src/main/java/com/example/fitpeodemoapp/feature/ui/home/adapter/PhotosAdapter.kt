package com.example.fitpeodemoapp.feature.ui.home.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.fitpeodemoapp.R
import com.example.fitpeodemoapp.core.utill.Constant.Companion.CATEGORY
import com.example.fitpeodemoapp.core.utill.Constant.Companion.DESCRIPTION
import com.example.fitpeodemoapp.core.utill.Constant.Companion.IMG_URL
import com.example.fitpeodemoapp.core.utill.Constant.Companion.NAME
import com.example.fitpeodemoapp.databinding.ItemPhotosListBinding
import com.example.fitpeodemoapp.feature.data.model.Photos
import com.example.fitpeodemoapp.feature.ui.Details.DetailActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_photos_list.view.*

//Adapter class to display data in list
class PhotosAdapter(private val context: Context) : RecyclerView.Adapter<PhotosAdapter.PhotosViewHolder>()  {

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
        holder.view.txtTitle.text = photos.name
        holder.view.txtURL.text = photos.desc

        //Image load in imageview
        Picasso.get()
            .load(photos.imageUrl)
            .placeholder(R.mipmap.ic_launcher)
            .error(R.mipmap.ic_launcher)
            .into(holder.view.imgPhotos)

        holder.view.cardMain.setOnClickListener {

            val intent = Intent(context, DetailActivity::class.java)
            var bundle = Bundle()

            // Put data in the bundle
            bundle.putString(NAME,photos.name)
            bundle.putString(CATEGORY,photos.category)
            bundle.putString(DESCRIPTION,photos.desc)
            bundle.putString(IMG_URL,photos.imageUrl)

            // Attach the bundle to the intent
            intent.putExtras(bundle)
            // Add the FLAG_ACTIVITY_NEW_TASK flag
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            // Start the activity with the intent
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return  differ.currentList.size
    }

    class PhotosViewHolder (val view : ItemPhotosListBinding): RecyclerView.ViewHolder(view.root)
}