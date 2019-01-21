package com.example.weatherapp

import android.content.Context
import android.graphics.Point
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.ProgressBar
import androidx.viewpager.widget.ViewPager
import com.mindorks.placeholderview.SwipeDecor
import com.mindorks.placeholderview.SwipePlaceHolderView
import com.mindorks.placeholderview.SwipeViewBuilder
import com.example.weatherapp.Utils
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread

class JokesActivity : AppCompatActivity(), JokeCard.Callback {
    private lateinit var mSwipeView: SwipePlaceHolderView
    private lateinit var mContext: Context
    private lateinit var spinner: ProgressBar

    companion object {
        val TAG = "MainActivity"
    }

    private val animationDuration = 300
    private var isToUndo = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jokes)
//
        mSwipeView = findViewById(R.id.swipe_view)
        mContext = applicationContext

        val category = intent.getStringExtra("category")
        title =  "$category jokes"


        val bottomMargin = Utils.dpToPx(160)
        val windowSize = Utils.getDisplaySize(windowManager)
        this.mSwipeView!!.getBuilder<SwipePlaceHolderView, SwipeViewBuilder<SwipePlaceHolderView>>()
            .setDisplayViewCount(3)
            .setIsUndoEnabled(true)
            .setSwipeType(2)
            .setHeightSwipeDistFactor(10f)
            .setWidthSwipeDistFactor(5f)
            .setSwipeDecor(SwipeDecor()
                .setViewWidth(windowSize.x)
                .setViewHeight(windowSize.y - bottomMargin)
                .setSwipeMaxChangeAngle(0f)
                .setSwipeRotationAngle(0)
                .setSwipeAnimTime(400)
                .setRelativeScale(0.05f))


        val cardViewHolderSize = Point(windowSize.x, windowSize.y - bottomMargin)

        val callBack = this

        doAsync {
            spinner = findViewById(R.id.progress_bar)

            spinner.visibility = View.VISIBLE

            var jokeList = ArrayList<JokesModel>()

            for(i in 1..10) {
                val url = "https://api.chucknorris.io/jokes/random?category=$category"
                val request = Request(url)
                val joke = request.getJokes()

                jokeList.add(joke)
            }


            uiThread {
                spinner = findViewById(R.id.progress_bar)

                spinner.visibility = View.GONE

                for (joke in jokeList) {
                    mSwipeView!!.addView(JokeCard(applicationContext, joke, cardViewHolderSize, callBack))

                    mSwipeView!!.addItemRemoveListener {
                        if (isToUndo) {
                            isToUndo = false
                            mSwipeView!!.undoLastSwipe()
                        }
                    }
                }
            }
        }


    }

    override fun onSwipeUp() {
       toast( "SUPER LIKE! Show any dialog here.")
        isToUndo = true
    }
}
