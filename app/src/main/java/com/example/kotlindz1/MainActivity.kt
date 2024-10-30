package com.example.kotlindz1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var fab: FloatingActionButton

    private val adapter = CardAdapter()

    companion object {
        var savedCards = ArrayList<Int>();
        var deletedCards = ArrayList<Int>();
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recycler_view)
        fab = findViewById(R.id.fab)

        recyclerView.adapter = adapter

        fab.setOnClickListener {
            if (!adapter.deletedIsEmpty()) {
                adapter.addItems(adapter.deletedNextToAdd())
            } else {
                adapter.addItems(adapter.itemCount + 1)
            }
        }

        if (savedInstanceState != null) {
            savedCards = savedInstanceState.getIntegerArrayList("savedList")!!
            deletedCards = savedInstanceState.getIntegerArrayList("deletedList")!!
            adapter.addAll(savedCards)
            adapter.deleted.addAll(deletedCards)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putIntegerArrayList("savedList", adapter.items)
        outState.putIntegerArrayList("deletedList", adapter.deleted)
    }
}