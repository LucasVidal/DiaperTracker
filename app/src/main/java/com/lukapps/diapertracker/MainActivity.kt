package com.lukapps.diapertracker

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        linearLayoutManager = LinearLayoutManager(this)
        historyRecyclerView.layoutManager = linearLayoutManager

        val history = listOf(
                HistoryItem("user_1", Date()),
                HistoryItem("user_2", Date())
        )

        historyRecyclerView.adapter = HistoryAdapter(history)
    }

    //https://diapertracker-1.firebaseio.com/
}
