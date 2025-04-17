package com.massir.listsort.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.massir.listsort.R
import com.massir.listsort.api.ListItem
import com.massir.listsort.component.TextBoxSimple

@Composable
fun OneList(
    listMap: Map<Int, List<ListItem>>,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        ItemCell(
            listId = stringResource(R.string.list_id),
            id = stringResource(R.string.id),
            name = stringResource(R.string.name),
            textColor = MaterialTheme.colorScheme.onTertiaryContainer,
            backgroundColor = MaterialTheme.colorScheme.tertiaryContainer,
        )

        LazyColumn(
            Modifier
                .fillMaxSize(),
        ) {
            listMap.forEach { (_, list) ->
                items(
                    count = list.size
                ) { index ->
                    ItemCell(
                        listId = list[index].listId,
                        id = list[index].id,
                        name = list[index].name,
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
        TextBoxSimple(
            listId.toString(),
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            textColor = textColor,
            backgroundColor = backgroundColor,
        )
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
