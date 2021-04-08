package com.example.crud

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.crud.adapters.CityAdapter
import com.example.crud.entities.City
import com.example.crud.viewModel.CityViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

private lateinit var cityViewModel:CityViewModel
    private val newWordActivityRequestCode =1



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = CityAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)// Add an observer on the LiveData returned by getAlphabetizedWords.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
       cityViewModel = ViewModelProvider(this).get(CityViewModel::class.java)
        cityViewModel.allCities.observe(this) { cities->cities?.let{adapter.setCities(it)}
        }

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, NewCityActivity::class.java)
            startActivityForResult(intent, newWordActivityRequestCode)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
// PERGUNTAR A PROFESSORA SOBRE O VAL CITY QUE NO TUTORIAL NAO TEM O ID E AQUI REQUERE MESMO SENDO AUTO INCREMENTADO
        if (requestCode == newWordActivityRequestCode && resultCode == Activity.RESULT_OK) {
            data?.getStringExtra(NewCityActivity.EXTRA_REPLY)?.let {
                val city = City(city =it, country = "Portugal")
                cityViewModel.insert(city)
            }
        } else {
            Toast.makeText(
                applicationContext,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.apagartudo -> {
                cityViewModel.deleteAll()
                true
            }



            R.id.todasCidades -> {

                // recycler view
                val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
                val adapter = CityAdapter(this)
                recyclerView.adapter = adapter
                recyclerView.layoutManager = LinearLayoutManager(this)

                // view model
                cityViewModel = ViewModelProvider(this).get(CityViewModel::class.java)
                cityViewModel.allCities.observe(this, Observer { cities ->
                    // Update the cached copy of the words in the adapter.
                    cities?.let { adapter.setCities(it) }
                })


                true
            }

            R.id.alterar -> {
                val city = City(id = 1, city = "xxx", country = "xxx")
                cityViewModel.updateCity(city)
                true
            }

            R.id.cidadesPortugal -> {

                // recycler view
                val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
                val adapter = CityAdapter(this)
                recyclerView.adapter = adapter
                recyclerView.layoutManager = LinearLayoutManager(this)

                // view model
                cityViewModel = ViewModelProvider(this).get(CityViewModel::class.java)

                cityViewModel.getCityFromCountry("Portugal")
                        .observe(this, Observer { cities ->cities?.let { adapter.setCities(it) }
                        })

                true
            }


            else -> super.onOptionsItemSelected(item)
        }
    }

}