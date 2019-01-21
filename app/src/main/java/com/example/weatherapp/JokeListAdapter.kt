package com.example.weatherapp


import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import org.jetbrains.anko.support.v4.find


class JokeListAdapter(private val joke: JokesModel, sm: FragmentManager) : FragmentStatePagerAdapter(sm) {
    override fun getCount(): Int = 3

    override fun getItem(position: Int): Fragment {
        return JokeSlideFragment(joke)
    }

}