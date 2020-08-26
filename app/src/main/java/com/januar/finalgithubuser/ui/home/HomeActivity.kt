package com.januar.finalgithubuser.ui.home

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.januar.finalgithubuser.R
import com.januar.finalgithubuser.database.remote.DatabaseHelper
import com.januar.finalgithubuser.ui.alarm.AlarmActivity
import com.januar.finalgithubuser.ui.favourite.FavouriteActivity
import com.januar.finalgithubuser.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    private lateinit var homeVM: HomeViewModel
    private lateinit var adapter: HomeAdapter
    private val etData: String = "Data"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        DatabaseHelper.getFavouriteDB(this)

        adapter = HomeAdapter()
        adapter.notifyDataSetChanged()

        if (savedInstanceState != null) {
            val data = savedInstanceState.getString(etData)

            search_bar.editText?.setText(data)
        }

        homeVM = ViewModelProvider(this).get(HomeViewModel::class.java)
        btn_search.setOnClickListener {
            getActionData()
        }

        search_bar.editText?.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(p0: TextView?, p1: Int, p2: KeyEvent?): Boolean {
                if (p1 == EditorInfo.IME_ACTION_SEND) {
                    getActionData()

                    return true
                }

                return false
            }

        })

        recycler_home.setHasFixedSize(true)

        getData()
    }

    private fun getActionData() {
        val search = search_bar.editText?.text.toString()

        if (search.isEmpty()) {
            Toast.makeText(this, resources.getString(R.string.search_bar_empty), Toast.LENGTH_SHORT)
                .show()
        } else {
            setProgress(true)
            setMessage(false)
            homeVM.setLiveData(search)
            getData()
        }
    }

    private fun getData() {
        homeVM.getLiveData().observe(this, Observer {
            if (it.total_count > 0) {
                adapter.setData(it.items)
                recycler_home.adapter = adapter
                setProgress(false)
                setMessage(false)
            } else {
                setProgress(false)
                setMessage(true)
            }
        })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putString(etData, search_bar.editText?.text.toString())
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.language_setting -> startActivity(Intent(Settings.ACTION_LOCALE_SETTINGS))
            R.id.favourite_user -> startActivity(Intent(this, FavouriteActivity::class.java))
            R.id.alarm_setting -> startActivity(Intent(this, AlarmActivity::class.java))
        }

        return super.onOptionsItemSelected(item)
    }

    private fun setProgress(condition: Boolean) {
        if (condition) {
            progressbar_home.visibility = View.VISIBLE
        } else {
            progressbar_home.visibility = View.INVISIBLE
        }
    }

    private fun setMessage(condition: Boolean) {
        if (condition) {
            empty_page.visibility = View.VISIBLE
        } else {
            empty_page.visibility = View.INVISIBLE
        }
    }
}