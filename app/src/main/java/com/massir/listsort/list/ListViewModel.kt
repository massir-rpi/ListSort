package com.massir.listsort.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    val listRepository: ListRepository,
) : ViewModel() {
    private val _uiStateFlow = MutableStateFlow(ListUiState())
    val uiStateFlow = _uiStateFlow.asStateFlow()

    fun load() {
        viewModelScope.launch {
            listRepository.getHiringList().body()?.let { response ->
                _uiStateFlow.value = _uiStateFlow.value.copy(
                    list = response
                )
            }
        }
    }
}