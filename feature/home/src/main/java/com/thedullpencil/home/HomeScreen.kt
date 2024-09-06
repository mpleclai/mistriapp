package com.thedullpencil.home

import androidx.compose.foundation.layout.Arrangement.SpaceEvenly
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.Icons.AutoMirrored.Filled
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.filledTonalButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.thedullpencil.common.components.InfoBlock
import com.thedullpencil.common.components.InfoItem
import com.thedullpencil.common.components.ToInfoCard
import com.thedullpencil.common.ui.theme.Dimens.PaddingL
import com.thedullpencil.common.ui.theme.toDp
import com.thedullpencil.domain.model.Season

@Preview
@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    val homeUiState by viewModel.uiState.collectAsState()

    Column(Modifier.padding(PaddingL.toDp())) {
        DateWidget(homeUiState)
        ProfileCard()
        Spacer(Modifier.padding(PaddingL.toDp()))
        RemindersSection()
    }
}

@Composable
fun DateWidget(homeUiState: HomeUiState) = Row(
    Modifier
        .fillMaxWidth()
        .padding(PaddingL.toDp()),
    horizontalArrangement = SpaceEvenly,
    verticalAlignment = Alignment.CenterVertically
) {
    DateWidgetButton(onClick = { /*TODO*/ }) {
        Icon(Filled.KeyboardArrowLeft, DECREMENT_DATE)
    }
    when (homeUiState) {
        is HomeUiState.HomeInfo -> Text(
            HomeUiState.HomeInfo().date.toDateString(),
            Modifier.padding(horizontal = PaddingL.toDp())
        )

        else -> Text(SELECTED_DATE, Modifier.padding(horizontal = PaddingL.toDp()))
    }

    DateWidgetButton(onClick = { /*TODO*/ }) {
        Icon(Filled.KeyboardArrowRight, INCREMENT_DATE)
    }
}

@Composable
fun DateWidgetButton(onClick: () -> Unit, content: @Composable () -> Unit) =
    Button(
        onClick,
        shape = RoundedCornerShape(PaddingL.toDp()),
        colors = filledTonalButtonColors()
    ) { content() }


@Composable
fun ProfileCard() = Card(Modifier.fillMaxWidth()) {
    InfoItem(name = SELECTED_PROFILE, icon = Icons.Filled.AccountCircle).ToInfoCard(false)
}

@Composable
fun RemindersSection() =
    InfoBlock(Modifier.fillMaxHeight(), REMINDERS, listOf(InfoItem(name = EMPTY)))

/** TODO - Move to R.string file for Home feature */
private const val SELECTED_PROFILE = "Selected Profile"
private const val INCREMENT_DATE = "Increment Date"
private const val DECREMENT_DATE = "Decrement Date"
private const val SELECTED_DATE = "Selected Date"
private const val REMINDERS = "Reminders"
private const val EMPTY = "Empty"

/** TODO - Move to DateUtil file under common */
fun Pair<Season, Int>.toDateString(): String = "${this.first} ${this.second}"
