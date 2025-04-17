package com.massir.listsort.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import com.massir.listsort.R

@Composable
fun TextBoxSimple(
    text: String,
    modifier: Modifier = Modifier,
    textColor: Color = Color.Unspecified,
    backgroundColor: Color = Color.Unspecified,
) {
    TextBoxCustom(
        modifier = modifier,
        backgroundColor = backgroundColor,
    ) {
        Text(
            text = text,
            color = textColor,
        )
    }
}

@Composable
fun TextBoxCustom(
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.Unspecified,
    content: @Composable (BoxScope.() -> Unit),
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
        content()
    }
}
