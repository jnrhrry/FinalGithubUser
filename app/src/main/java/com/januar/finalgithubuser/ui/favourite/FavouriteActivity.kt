package com.januar.finalgithubuser.ui.favourite

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.januar.finalgithubuser.R
import com.januar.finalgithubuser.viewmodel.FavouriteViewModel
import kotlinx.android.synthetic.main.activity_favourite.*

class FavouriteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favourite)

        supportActionBar?.title = resources.getString(R.string.favourite_page)
        val vm = ViewModelProvider(this).get(FavouriteViewModel::class.java)

        vm.getData(this).observe(this, Observer {
            if (it == null){
                Toast.makeText(this, resources.getString(R.string.favourite_page_error), Toast.LENGTH_SHORT).show()
            } else{
                recycler_favourite.adapter = FavouriteAdapter(it)
            }
        })
    }
}