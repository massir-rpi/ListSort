package com.massir.listsort.list

import com.massir.listsort.api.FetchHiringService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ListRepository @Inject constructor(
    private val fetchHiringService: FetchHiringService,
) {
    suspend fun getHiringList() = fetchHiringService.getHiringList()
}
