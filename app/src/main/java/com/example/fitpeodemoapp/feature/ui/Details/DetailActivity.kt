package com.example.fitpeodemoapp.feature.ui.Details

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.fitpeodemoapp.R
import com.example.fitpeodemoapp.core.utill.Constant.Companion.CATEGORY
import com.example.fitpeodemoapp.core.utill.Constant.Companion.DESCRIPTION
import com.example.fitpeodemoapp.core.utill.Constant.Companion.IMG_URL
import com.example.fitpeodemoapp.core.utill.Constant.Companion.NAME
import com.example.fitpeodemoapp.databinding.ActivityDetailBinding
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    //Declare variables
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)

        setupUI()
    }

    private fun setupUI() {
        // Retrieve the bundle data
        val bundle = intent.extras
        if (bundle != null) {
            // Retrieve data from the bundle
            val name = bundle.getString(NAME)
            val category = bundle.getString(CATEGORY)
            val imgURL = bundle.getString(IMG_URL)
            val description = bundle.getString(DESCRIPTION)

            binding.txtName.text = name
            binding.txtCategory.text =category
            binding.txtDesc.text =description

            //Image load in imageview
            Picasso.get()
                .load(imgURL)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(binding.imgDetails)

        }
    }
}