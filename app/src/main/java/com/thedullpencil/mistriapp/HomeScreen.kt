package com.thedullpencil.mistriapp

import androidx.compose.foundation.layout.Arrangement.SpaceEvenly
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.Icons.AutoMirrored.Filled
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.thedullpencil.common.components.InfoBlock
import com.thedullpencil.common.components.InfoItem
import com.thedullpencil.common.components.ToInfoCard
import com.thedullpencil.common.ui.theme.Dimens.PaddingL
import com.thedullpencil.common.ui.theme.toDp

@Preview
@Composable
fun HomeScreen() = Column(Modifier.padding(PaddingL.toDp())) {
    DateWidget()
    ProfileCard()
    Spacer(Modifier.padding(PaddingL.toDp()))
    RemindersSection()
}

@Composable
fun DateWidget() = Row(
    Modifier
        .fillMaxWidth()
        .padding(PaddingL.toDp()),
    horizontalArrangement = SpaceEvenly
) {
    Icon(Filled.KeyboardArrowLeft, "Decrement Date")
    Text("Selected Date", Modifier.padding(horizontal = PaddingL.toDp()))
    Icon(Filled.KeyboardArrowRight, "Increment Date")
}

@Composable
fun ProfileCard() = Card(Modifier.fillMaxWidth()) {
    InfoItem(name = "Selected Profile", icon = Icons.Filled.AccountCircle).ToInfoCard(false)
}

@Composable
fun RemindersSection() = InfoBlock(
    Modifier.fillMaxHeight(),
    "Reminders",
    listOf(InfoItem(name = "Empty"))
)