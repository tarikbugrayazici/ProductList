package com.example.productlist.domain.repository.preferences

import android.content.Context

interface StateSharedPrefRepository {
    fun getContextPref(): Context
}