package com.massir.listsort.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.massir.listsort.R
import com.massir.listsort.api.ListItem
import com.massir.listsort.component.TextBoxSimple
import com.massir.listsort.component.TextBoxCustom

@Composable
fun TabulatedList(
    listMap: Map<Int, List<ListItem>>,
    listIdSelected: Int,
    onListIdSelected: ((Int) -> Unit),
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        ListSelector(
            listIds = listMap.keys.sorted(),
            listIdSelected = listIdSelected,
            onListIdSelected = onListIdSelected,
        )

        HorizontalDivider()

        listMap[listIdSelected]?.let { listId ->
            ItemList(
                list = listId,
                modifier = Modifier.fillMaxSize(),
            )
        }
    }
}

@Composable
private fun ListSelector(
    listIds: List<Int>,
    listIdSelected: Int,
    onListIdSelected: ((Int) -> Unit),
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
    ) {
        listIds.forEach { listId ->
            val thisIdSelected = listIdSelected == listId
            TextBoxCustom(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .clickable { onListIdSelected(listId) },
                backgroundColor = if (thisIdSelected) MaterialTheme.colorScheme.secondaryContainer else Color.Unspecified,
            ) {
                Text(
                    text = stringResource(R.string.list_x, listId),
                    style = MaterialTheme.typography.headlineSmall,
                )
            }
        }
    }
}

@Composable
private fun ItemList(
    list: List<ListItem>,
    modifier: Modifier = Modifier,
) {
    ItemCell(
        id = stringResource(R.string.id),
        name = stringResource(R.string.name),
        textColor = MaterialTheme.colorScheme.onTertiaryContainer,
        backgroundColor = MaterialTheme.colorScheme.tertiaryContainer,
    )

    LazyColumn(
        modifier = modifier
    ) {
        items(
            count = list.size
        ) { index ->
            ItemCell(
                id = list[index].id,
                name = list[index].name,
            )
        }
    }
}

@Composable
private fun ItemCell(
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
        TextBoxSimple(
            id.toString(),
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            textColor = textColor,
            backgroundColor = backgroundColor,
        )
        TextBoxSimple(
            name.toString(),
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            textColor = textColor,
            backgroundColor = backgroundColor,
        )
    }
}
