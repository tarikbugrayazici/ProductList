package com.example.productlist.domain.repository.preferences

import com.example.productlist.App
import javax.inject.Inject

class StateSharedPrefRepositoryImpl @Inject constructor(val context: App) : StateSharedPrefRepository {

    override fun getContextPref(): App {
        return context
    }
}