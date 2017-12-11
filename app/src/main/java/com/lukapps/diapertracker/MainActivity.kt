package com.lukapps.diapertracker

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: HistoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        linearLayoutManager = LinearLayoutManager(this)
        historyRecyclerView.layoutManager = linearLayoutManager

        adapter = HistoryAdapter(emptyList())
        historyRecyclerView.adapter = adapter

        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("historyItems");
        myRef.addChildEventListener(object : ChildEventListener {

            override fun onChildMoved(dataSnapshot: DataSnapshot?, previousChildName: String?) { }
            override fun onChildChanged(dataSnapshot: DataSnapshot?, previousChildName: String?) { }
            override fun onCancelled(databaseError: DatabaseError?) {
                databaseError?.let {
                    Toast.makeText(this@MainActivity, databaseError.message,
                            Toast.LENGTH_SHORT).show()
                    Log.e(MainActivity::class.java.simpleName,
                            databaseError.message, databaseError.toException())
                }
            }

            override fun onChildRemoved(dataSnapshot: DataSnapshot?) {
                val historyItem = dataSnapshot?.getValue(HistoryItem::class.java)
                historyItem?.let {
                    adapter.remove(it)
                }
            }

            override fun onChildAdded(dataSnapshot: DataSnapshot?, previousChildName: String?) {
                val historyItem = dataSnapshot?.getValue(HistoryItem::class.java)
                historyItem?.let {
                    adapter.add(it)
                }
            }
        })

        trackBtn.setOnClickListener({
            val childRef = myRef.push()
            val historyItem = HistoryItem(userNameTxt.text.toString(), Date())

            childRef.setValue(historyItem)
        })

    }

    //https://diapertracker-1.firebaseio.com/
}
