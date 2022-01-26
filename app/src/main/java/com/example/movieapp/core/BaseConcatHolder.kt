package com.example.movieapp.core

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseConcatHolder<T>(val itemView: View): RecyclerView.ViewHolder(itemView) {
    abstract fun bind(adapter: T)
}