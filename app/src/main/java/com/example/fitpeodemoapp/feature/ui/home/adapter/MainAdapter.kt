package com.example.fitpeodemoapp.feature.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fitpeodemoapp.R
import com.example.fitpeodemoapp.feature.data.model.Photos
import kotlinx.android.synthetic.main.item_photos_list.view.*

class MainAdapter(
    private val users: ArrayList<Photos>
) : RecyclerView.Adapter<MainAdapter.DataViewHolder>()  {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(user: Photos) {
            itemView.txtTitle.text = user.title
            itemView.txtURL.text = user.url
            Glide.with(itemView.imgPhotos.context)
                .load(user.url)
                .into(itemView.imgPhotos)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_photos_list, parent,
                false
            )
        )

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(users[position])

    fun addData(list: List<Photos>) {
        users.addAll(list)
    }
}