package com.example.kotlindz1

import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CardViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val text = view.findViewById<TextView>(R.id.text_card)
    val image = view.findViewById<ImageView>(R.id.image_card)

    fun bind(number: Int) {
        text.text = "$number"
        if (number % 2 == 0) {
            image.setBackgroundColor(Color.RED)
        } else {
            image.setBackgroundColor(Color.BLUE)
        }
    }

}