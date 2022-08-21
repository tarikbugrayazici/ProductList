package com.example.productlist.domain.usecase.preferencesusecase

import android.content.Context
import com.example.productlist.domain.repository.preferences.StateSharedPrefRepository
import com.example.productlist.util.Constants
import javax.inject.Inject

class SetPreferencesUseCase @Inject constructor(
    private val repository: StateSharedPrefRepository
) {
    operator fun invoke(isLinearLayoutManager: Boolean) {
        val context = repository.getContextPref()
        val pref = context.getSharedPreferences(
            Constants.SHARED_PREFERENCES_NAME,
            Context.MODE_PRIVATE
        )
        val editor = pref.edit()
        editor.putBoolean(Constants.LAYOUT_MANAGER, isLinearLayoutManager)
        editor.apply()
    }
}