package com.massir.listsort.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.massir.listsort.R
import com.massir.listsort.api.ListItem
import com.massir.listsort.ui.theme.ListSortTheme

private const val SCREEN_NAME = "ListScreen"

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
        onTabViewToggled = { viewModel.setIsTabViewToggledOn(it) },
        onListIdSelected = { viewModel.setListIdSelected(it) }
    )
}

@Preview(group = SCREEN_NAME)
@Composable
fun OneListPreview() {
    ListScreenPreview(
        isTabViewToggledOn = false
    )
}

@Preview(group = SCREEN_NAME)
@Composable
fun TabListsPreview() {
    ListScreenPreview(
        isTabViewToggledOn = true
    )
}

@Composable
private fun ListScreenPreview(
    isTabViewToggledOn: Boolean,
) {
    ListSortTheme {
        Screen(
            uiState = ListUiState(
                isTabViewToggledOn = isTabViewToggledOn,
                listMap = listOf(
                    ListItem(51, 3, "Item 51"),
                    ListItem(5321,1,"Item 5321"),
                    ListItem(1642, 2, "Item 1642"),
                    ListItem(621,1, "Item 621"),
                )
                    .sortedWith(compareBy({ it.listId }, { it.id }))
                    .groupBy { it.listId ?: -1 },
            ),
            onTabViewToggled = {},
            onListIdSelected = {},
        )
    }
}

@Composable
private fun Screen (
    uiState: ListUiState,
    onTabViewToggled: ((Boolean) -> Unit),
    onListIdSelected: ((Int) -> Unit),
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier.then(
            Modifier
                .fillMaxSize(),
        ),
    ) { innerPadding ->
        Box(
            Modifier
                .padding(innerPadding)
                .fillMaxSize(),
        ) {
            if (uiState.isTabViewToggledOn) {
                TabulatedList(
                    listMap = uiState.listMap,
                    listIdSelected = uiState.listIdSelected,
                    onListIdSelected = onListIdSelected,
                )
            } else {
                OneList(
                    listMap = uiState.listMap,
                )
            }
            ToggleViewButton(
                isToggledOn = uiState.isTabViewToggledOn,
                onToggle = onTabViewToggled,
                modifier = Modifier
                    .padding(dimensionResource(R.dimen.small_padding))
                    .size(dimensionResource(R.dimen.medium_size))
                    .align(Alignment.BottomEnd),
            )
        }
    }
}

@Composable
private fun ToggleViewButton(
    isToggledOn: Boolean,
    onToggle: ((Boolean) -> Unit),
    modifier: Modifier = Modifier,
) {
    IconToggleButton(
        checked = isToggledOn,
        onCheckedChange = onToggle,
        modifier = modifier.then(
            Modifier
                .background(
                    color =  if (isToggledOn) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.errorContainer,
                    shape = CircleShape,
                ),
        ),
    ) {
        Icon(
            painter = if (isToggledOn) painterResource(R.drawable.select_window) else painterResource(R.drawable.select_window_off),
            tint = if (isToggledOn) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onErrorContainer,
            contentDescription = stringResource(R.string.toggle_view),
            modifier = Modifier
                .size(dimensionResource(R.dimen.small_size)),
        )
    }
}
