package com.example.productlist.util.extension

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.findLastCompletelyVisibleItemPosition(): Int =
    if (layoutManager is GridLayoutManager) {
        (layoutManager as GridLayoutManager).findLastVisibleItemPosition()
    } else {
        (layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
    }
