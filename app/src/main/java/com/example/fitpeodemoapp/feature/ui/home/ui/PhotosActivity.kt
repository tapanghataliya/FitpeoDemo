package com.example.fitpeodemoapp.feature.ui.home.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fitpeodemoapp.R
import com.example.fitpeodemoapp.core.utill.Status
import com.example.fitpeodemoapp.core.utill.ViewExt.Companion.showSnackBar
import com.example.fitpeodemoapp.databinding.ActivityMainBinding
import com.example.fitpeodemoapp.feature.data.model.Photos
import com.example.fitpeodemoapp.feature.ui.home.adapter.PhotosAdapter
import com.example.fitpeodemoapp.feature.ui.home.viewmodel.PhotosViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

@AndroidEntryPoint
class PhotosActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val photoViewModel : PhotosViewModel by viewModels()
    private lateinit var photoAdapter: PhotosAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setupUI()
        setupObserver()
    }

    //Display data in recyclerview
    private fun setupUI() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        photoAdapter = PhotosAdapter()
        binding.recyclerView.adapter = photoAdapter
    }

    //Check data from API and display in screen
    private fun setupObserver() {
        photoViewModel.photos.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    it.data?.let { photos -> renderList(photos) }
                    binding.recyclerView.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
                }
                Status.ERROR -> {
                    //Handle Error
                    binding.progressBar.visibility = View.GONE
                    val myView = findViewById<View>(android.R.id.content)
                    myView.showSnackBar(it.message)
                    binding.imgNoInternet.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
                }
            }
        })
    }

    //Rendering the list from adapter class
    @SuppressLint("NotifyDataSetChanged")
    private fun renderList(photo: List<Photos>) {
        photoAdapter.photosList(photo)
        photoAdapter.notifyDataSetChanged()
    }
}