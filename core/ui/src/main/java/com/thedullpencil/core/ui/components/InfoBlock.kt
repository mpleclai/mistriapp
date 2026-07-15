package com.thedullpencil.core.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.thedullpencil.core.ui.theme.Dimens.PaddingM
import com.thedullpencil.core.ui.theme.Dimens.PaddingS
import com.thedullpencil.core.ui.theme.toDp

@Composable
fun InfoBlock(
    modifier: Modifier = Modifier,
    header: String? = null,
    items: List<InfoItem> = emptyList()
) = Card(modifier.fillMaxWidth()) {
    header?.let { Header(header) }
    LazyColumn {
        itemsIndexed(
            items = items,
            key = { index, item -> item.id ?: "${item.derivedKey()}#$index" }
        ) { _, item ->
            item.ToListItem()
        }
    }
}

@Composable
fun InfoCardListBlock(
    modifier: Modifier = Modifier,
    items: List<InfoItem> = emptyList()
) = LazyColumn(modifier = modifier.fillMaxWidth()) {
    itemsIndexed(
        items = items,
        key = { index, item -> item.id ?: "${item.derivedKey()}#$index" }
    ) { _, item ->
        Card(Modifier.fillMaxWidth().padding(vertical = PaddingS.toDp())) {
            item.ToListItem(showDivider = false)
        }
    }
}

private fun InfoItem.derivedKey(): String =
    listOfNotNull(name, value).joinToString(separator = "|").ifEmpty { "info_item" }

@Composable
private fun Header(header: String) =
    Text(header, Modifier.padding(PaddingM.toDp()), style = typography.titleLarge)
