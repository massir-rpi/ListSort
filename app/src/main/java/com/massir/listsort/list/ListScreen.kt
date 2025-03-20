package com.massir.listsort.list

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.massir.listsort.R
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
                .padding(innerPadding),
        ) {
            ItemCell(
                listId = stringResource(R.string.list_id),
                id = stringResource(R.string.id),
                name = stringResource(R.string.name),
                textColor = MaterialTheme.colorScheme.onPrimaryContainer,
                backgroundColor = MaterialTheme.colorScheme.primaryContainer
            )
            LazyColumn(
                Modifier
                    .fillMaxSize(),
            ) {
                items(
                    count = uiState.list.size
                ) { index ->
                    ItemCell(
                        listId = uiState.list[index].listId,
                        id = uiState.list[index].id,
                        name = uiState.list[index].name,
                    )
                }
            }
        }
    }
}

@Composable
private fun ItemCell(
    listId: Any?,
    id: Any?,
    name: Any?,
    modifier: Modifier = Modifier,
    textColor: Color = Color.Unspecified,
    backgroundColor: Color = Color.Unspecified,
) {
    Row(
        modifier = modifier.then(
            Modifier
                .fillMaxWidth(),
        )
    ) {
        TextBox(
            listId.toString(),
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            textColor = textColor,
            backgroundColor = backgroundColor,
        )
        TextBox(
            id.toString(),
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            textColor = textColor,
            backgroundColor = backgroundColor,
        )
        TextBox(
            name.toString(),
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            textColor = textColor,
            backgroundColor = backgroundColor,
        )
    }
}

@Composable
private fun TextBox(
    text: String,
    modifier: Modifier = Modifier,
    textColor: Color = Color.Unspecified,
    backgroundColor: Color = Color.Unspecified,
) {
    Box(
        modifier = modifier.then(
            Modifier
                .background(backgroundColor)
                .border(
                    width = dimensionResource(R.dimen.border),
                    color = MaterialTheme.colorScheme.outline,
                )
                .padding(dimensionResource(R.dimen.tiny_padding))
        )
    ) {
        Text(
            text = text,
            color = textColor,
        )
    }
}
