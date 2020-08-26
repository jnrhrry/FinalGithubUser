package com.januar.consumerapp.ui.favourite

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.januar.consumerapp.R
import com.januar.consumerapp.viewmodel.FavouriteViewModel
import kotlinx.android.synthetic.main.activity_favourite.*

class FavouriteActivity : AppCompatActivity() {

    private lateinit var vm: FavouriteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favourite)

        supportActionBar?.title = resources.getString(R.string.app_name)
        vm = ViewModelProvider(this).get(FavouriteViewModel::class.java)
        getConsumerData()
    }

    private fun getConsumerData(){
        vm.getData(this).observe(this, Observer {
            if (it == null) Toast.makeText(this, resources.getString(R.string.favourite_page_error), Toast.LENGTH_SHORT).show()
            else recycler_favourite.adapter = FavouriteAdapter(it)
        })
    }

    override fun onResume() {
        super.onResume()
        getConsumerData()
    }


}