package com.example.animationincompose.hadith_api.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animationincompose.hadith_api.domain.model.Hadith
import com.example.animationincompose.hadith_api.domain.repository.HadithRepository
import com.example.animationincompose.hadith_api.domain.use_cases.generate_hadith.GenerateHadithUseCase
import com.example.animationincompose.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HadithViewModel @Inject constructor(
    private val getHadith: GenerateHadithUseCase
): ViewModel() {
    private val _state = MutableStateFlow<Resource<Hadith>>(Resource.Idle)
    val hadithState: StateFlow<Resource<Hadith>> = _state.asStateFlow()

    fun getHadith() {
        viewModelScope.launch {
            _state.value = Resource.Loading
            try {
                val hadith = getHadith.invoke()
                _state.value = Resource.Success(hadith)
            } catch (e: Exception) {
                _state.value = Resource.Error(e.message ?: "Unknown error occurred")
            }
        }
    }
}