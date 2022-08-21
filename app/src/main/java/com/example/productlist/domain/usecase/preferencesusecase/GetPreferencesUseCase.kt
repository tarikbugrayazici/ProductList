package com.example.productlist.domain.usecase.preferencesusecase

import android.content.Context
import com.example.productlist.domain.repository.preferences.StateSharedPrefRepository
import com.example.productlist.util.Constants
import javax.inject.Inject

class GetPreferencesUseCase @Inject constructor(
    private val repository: StateSharedPrefRepository
) {
    operator fun invoke(): Boolean {
        val context = repository.getContextPref()
        val pref = context.getSharedPreferences(
            Constants.SHARED_PREFERENCES_NAME,
            Context.MODE_PRIVATE
        )
        val state = pref.getBoolean(Constants.LAYOUT_MANAGER, true)
        return state
    }
}