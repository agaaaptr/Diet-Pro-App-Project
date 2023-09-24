package com.example.dietproapp.util

import androidx.recyclerview.widget.RecyclerView
import android.view.View

fun RecyclerView.addOnItemClickListener(onClickListener: (position: Int, view: View) -> Unit) {
    this.addOnChildAttachStateChangeListener(object : RecyclerView.OnChildAttachStateChangeListener {
        override fun onChildViewAttachedToWindow(view: View) {
            view.setOnClickListener {
                val holder = getChildViewHolder(view)
                onClickListener.invoke(holder.adapterPosition, view)
            }
        }

        override fun onChildViewDetachedFromWindow(view: View) {
            // Nothing to do here
        }
    })
}
