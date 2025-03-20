package com.massir.listsort.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.massir.listsort.api.ListItem
import com.massir.listsort.ui.theme.ListSortTheme

@Composable
fun ListScreen(
    navController: NavController,
    viewModel: ListViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiStateFlow.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.load()
    }

    Screen(
        uiState = uiState,
    )
}

@Preview
@Composable
fun ListScreenPreview() {
    ListSortTheme {
        Screen(
            uiState = ListUiState(
                list = listOf(
                    ListItem(5321,1,"Item 5321"),
                    ListItem(1642, 2, "Item 1642"),
                    ListItem(621,1, "Item 621"),
                ),
            ),
        )
    }
}

@Composable
private fun Screen (
    uiState: ListUiState,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier.then(
            Modifier
                .fillMaxSize(),
        ),
    ) { innerPadding ->
        Column(
            Modifier
                .padding(innerPadding)
                .fillMaxSize(),
        ) {
            uiState.list.forEach { item ->
                Row {
                    Text(item.listId.toString())
                    Text(item.id.toString())
                    Text(item.name.toString())
                }
            }
        }
    }
}