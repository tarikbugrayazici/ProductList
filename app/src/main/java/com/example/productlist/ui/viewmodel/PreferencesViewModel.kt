package com.example.productlist.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.productlist.domain.usecase.preferencesusecase.GetPreferencesUseCase
import com.example.productlist.domain.usecase.preferencesusecase.SetPreferencesUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class PreferencesViewModel @Inject constructor(
    private val setPreferencesUseCase: SetPreferencesUseCase,
    private val getPreferencesUseCase: GetPreferencesUseCase
) : ViewModel() {


    fun setIsLayoutLinear(isLinear: Boolean) {
        setPreferencesUseCase.invoke(isLinear)
    }

    fun getIsLayoutLinear(): Boolean {
        return getPreferencesUseCase.invoke()
    }


}