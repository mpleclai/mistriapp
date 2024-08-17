package com.thedullpencil.common.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.thedullpencil.common.ui.theme.Dimens.PaddingM
import com.thedullpencil.common.ui.theme.toDp

@Composable
fun InfoBlock(
    modifier: Modifier = Modifier,
    header: String? = null,
    items: List<InfoItem> = emptyList()
) = Card(modifier.fillMaxWidth()) {
    header?.let { Header(header) }
    for (item in items) item.ToInfoCard()
}

@Composable
private fun Header(header: String) =
    Text(header, Modifier.padding(PaddingM.toDp()), style = typography.titleLarge)
