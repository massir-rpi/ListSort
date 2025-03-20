package com.massir.listsort.api

import kotlinx.serialization.Serializable

@Serializable
data class ListItem(
    val id: Int? = null,
    val listId: Int? = null,
    val name: String? = null,
)
