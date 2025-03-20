package com.massir.listsort.list

import com.massir.listsort.api.ListItem

data class ListUiState (
    val list: List<ListItem> = emptyList<ListItem>(),
)
