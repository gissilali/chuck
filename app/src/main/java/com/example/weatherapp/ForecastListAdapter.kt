package com.example.weatherapp

import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView
import com.squareup.picasso.Picasso
import org.jetbrains.anko.find

class ForecastListAdapter (private val items: List<String>, private val itemClick : (String) -> Unit) : androidx.recyclerview.widget.RecyclerView.Adapter<ForecastListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.category_item, parent, false)
        return ViewHolder(view, itemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nameView.text = items[position].capitalize()
        holder.bindForecast(items[position])
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(val view: View, val itemClick: (String) -> Unit) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {
        val nameView: TextView = view.find(R.id.category_name)
        private val listItem: LinearLayout = view.find(R.id.list_item)

        fun bindForecast(categoryName: String) {
            with(categoryName) {
                 view.setOnClickListener { itemClick(this) }
            }
        }

    }
}