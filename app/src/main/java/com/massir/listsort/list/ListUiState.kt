package com.massir.listsort.list

import com.massir.listsort.api.ListItem

data class ListUiState (
    val listMap: Map<Int, List<ListItem>> = emptyMap(),
    val isTabViewToggledOn: Boolean = false,
    val listIdSelected: Int = 1,
)
