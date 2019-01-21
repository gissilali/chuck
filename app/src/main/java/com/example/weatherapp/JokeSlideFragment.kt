package com.example.weatherapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager

@SuppressLint("ValidFragment")
class JokeSlideFragment(private  val jokesModel: JokesModel) : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.joke_item, container, false)
        view.findViewById<TextView>(R.id.joke_text).text = jokesModel.value
        return view
    }
}