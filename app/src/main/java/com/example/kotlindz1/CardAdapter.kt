package com.example.kotlindz1

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class CardAdapter() : RecyclerView.Adapter<CardViewHolder>() {

    val items = ArrayList<Int>();
    val deleted = ArrayDeque<Int>();

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        return CardViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bind(items[position])
        holder.itemView.setOnClickListener {
            deleted.addLast(items[position])
            removeItem(position)
        }
    }

    fun deletedIsEmpty(): Boolean {
        return deleted.isEmpty()
    }

    fun deletedNextToAdd(): Int {
        val nextToAdd = deleted.removeFirst()
        return nextToAdd
    }

    fun addItems(item: Int) {
        items.add(item)
        notifyDataSetChanged()
    }

    fun addAll(item: ArrayList<Int>) {
        items.addAll(item)
        notifyDataSetChanged()
    }

    fun removeItem(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, items.size)
    }
}