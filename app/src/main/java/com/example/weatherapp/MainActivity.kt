package com.example.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import android.content.Intent



class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: androidx.recyclerview.widget.RecyclerView
    private lateinit var viewAdapter: androidx.recyclerview.widget.RecyclerView.Adapter<*>
    private lateinit var viewManager: androidx.recyclerview.widget.RecyclerView.LayoutManager

    private var url = "https://api.chucknorris.io/jokes/categories"

    private var items:List<String> = listOf();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        doAsync {
            val request = Request(url)
            val categories = request.getCategories()
            uiThread {
                viewManager = androidx.recyclerview.widget.LinearLayoutManager(parent)
                viewAdapter = ForecastListAdapter(items = categories.categories) {
//                    toast(it);
                    gotoJokes(it);

                }
                recyclerView = findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.forecast_list).apply {
                    // use this setting to improve performance if you know that changes
                    // in content do not change the layout size of the RecyclerView
                    setHasFixedSize(true)

                    // use a linear layout manager
                    layoutManager = viewManager

                    // specify an viewAdapter (see also next example)
                    adapter = viewAdapter

                }
            }
        }

    }

    private fun gotoJokes(category: String) {
        try {
            val intent = Intent(this@MainActivity, JokesActivity::class.java)
            intent.putExtra("category", category)
            startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

}
