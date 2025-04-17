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
    private val listRepository: ListRepository,
) : ViewModel() {
    private val _uiStateFlow = MutableStateFlow(ListUiState())
    val uiStateFlow = _uiStateFlow.asStateFlow()

    fun load() {
        viewModelScope.launch {
            listRepository.getHiringList().body()?.let { response ->
                val listSorted = response
                    .filter { it.name != null && it.name != ""}
                    .sortedWith(compareBy({ it.listId }, { it.id }))
                _uiStateFlow.value = _uiStateFlow.value.copy(
                    listMap = listSorted.groupBy { it.listId ?: -1 },
                    listIdSelected = listSorted.first().listId ?: -1,
                )
            }
        }
    }

    fun setIsTabViewToggledOn(isOn: Boolean) {
        _uiStateFlow.value = _uiStateFlow.value.copy(
            isTabViewToggledOn = isOn
        )
    }

    fun setListIdSelected(listId: Int) {
        _uiStateFlow.value = _uiStateFlow.value.copy(
            listIdSelected = listId,
        )
    }
}
