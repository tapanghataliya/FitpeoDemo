package com.example.fitpeodemoapp.feature.ui.home.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fitpeodemoapp.R
import com.example.fitpeodemoapp.core.utill.Status
import com.example.fitpeodemoapp.core.utill.ViewExt.Companion.showSnackBar
import com.example.fitpeodemoapp.feature.data.model.Photos
import com.example.fitpeodemoapp.feature.ui.home.adapter.MainAdapter
import com.example.fitpeodemoapp.feature.ui.home.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel : MainViewModel by viewModels()
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupUI()
        setupObserver()
    }

    private fun setupUI() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter(arrayListOf())
        recyclerView.adapter = adapter
    }

    private fun setupObserver() {
        mainViewModel.users.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    it.data?.let { users -> renderList(users) }
                    recyclerView.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                }
                Status.ERROR -> {
                    //Handle Error
                    progressBar.visibility = View.GONE
                    val myView = findViewById<View>(android.R.id.content)
                    myView.showSnackBar(it.message)
                    imgNoInternet.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                }
            }
        })
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun renderList(users: List<Photos>) {
        adapter.addData(users)
        adapter.notifyDataSetChanged()
    }
}