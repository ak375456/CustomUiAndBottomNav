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
    private val getHadith: GenerateHadithUseCase,
    private val repository: HadithRepository
): ViewModel() {
    //api state
    private val _state = MutableStateFlow<Resource<Hadith>>(Resource.Idle)
    val hadithState: StateFlow<Resource<Hadith>> = _state.asStateFlow()

    // State for offline Hadiths
    private val _offlineHadiths = MutableStateFlow<Resource<List<Hadith>>>(Resource.Idle)
    val offlineHadiths: StateFlow<Resource<List<Hadith>>> = _offlineHadiths.asStateFlow()

    // State for a specific Hadith
    private val _selectedHadith = MutableStateFlow<Resource<Hadith>>(Resource.Idle)
    val selectedHadith: StateFlow<Resource<Hadith>> = _selectedHadith.asStateFlow()

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

    // Fetch offline Hadiths from the local database
    fun fetchOfflineHadiths() {
        viewModelScope.launch {
            _offlineHadiths.value = Resource.Loading
            try {
                repository.getAllHadiths().collect { hadiths ->
                    _offlineHadiths.value = Resource.Success(hadiths)
                }
            } catch (e: Exception) {
                _offlineHadiths.value = Resource.Error(e.message ?: "Unknown error occurred")
            }
        }
    }


    // Fetch a specific Hadith by ID (when clicked)
    fun fetchHadithById(hadithId: Int) {
        viewModelScope.launch {
            _selectedHadith.value = Resource.Loading
            try {
                val hadith = repository.getHadithById(hadithId)
                _selectedHadith.value = Resource.Success(hadith) as Resource<Hadith>
            } catch (e: Exception) {
                _selectedHadith.value = Resource.Error(e.message ?: "Unknown error occurred")
            }
        }
    }
}