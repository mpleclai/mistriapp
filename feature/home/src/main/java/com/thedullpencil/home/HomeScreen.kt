package com.thedullpencil.home

import androidx.compose.foundation.layout.Arrangement.SpaceEvenly
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons.AutoMirrored
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.filledTonalButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.thedullpencil.common.components.InfoBlock
import com.thedullpencil.common.components.InfoItem
import com.thedullpencil.common.components.ToInfoCard
import com.thedullpencil.common.ui.theme.Dimens.PaddingL
import com.thedullpencil.common.ui.theme.Dimens.PaddingS
import com.thedullpencil.common.ui.theme.toDp
import com.thedullpencil.core.util.MistriappDate
import com.thedullpencil.core.util.getNextDate
import com.thedullpencil.core.util.getPreviousDate
import com.thedullpencil.core.util.toDateString
import com.thedullpencil.home.HomeUiState.Empty
import com.thedullpencil.home.HomeUiState.HomeInfo
import com.thedullpencil.home.R.string.feature_home_decrement_date
import com.thedullpencil.home.R.string.feature_home_empty
import com.thedullpencil.home.R.string.feature_home_increment_date
import com.thedullpencil.home.R.string.feature_home_reminders
import com.thedullpencil.home.R.string.feature_home_select_profile
import com.thedullpencil.home.R.string.feature_home_selected_date

@Preview
@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    val homeUiState by viewModel.uiState.collectAsState()

    Column(Modifier.padding(PaddingL.toDp())) {
        when (homeUiState) {
            is Empty -> {
                val selectProfile = stringResource(feature_home_select_profile)
                Card(Modifier.fillMaxWidth()) {
                    InfoItem(name = selectProfile, icon = Filled.Add).ToInfoCard(false)
                }
            }

            is HomeInfo -> {
                with(viewModel) {
                    DateWidget(homeUiState as HomeInfo)
                    Spacer(Modifier.padding(PaddingS.toDp()))
                    ProfileCard(homeUiState as HomeInfo)
                    Spacer(Modifier.padding(PaddingL.toDp()))
                    RemindersSection()
                }
            }

            else -> CircularProgressIndicator()
        }
    }
}

@Composable
fun HomeViewModel.DateWidget(homeUiState: HomeInfo) = Row(
    Modifier
        .fillMaxWidth()
        .padding(PaddingL.toDp()),
    horizontalArrangement = SpaceEvenly,
    verticalAlignment = CenterVertically
) {
    val date = remember{ mutableStateOf(
        MistriappDate(homeUiState.selectedProfile.currentDate, homeUiState.selectedProfile.currentYear)
    ) }
    with(date.value) {
        DateWidgetButton(onClick = { date.value = MistriappDate(day, year).getPreviousDate() }) {
            Icon(
                AutoMirrored.Filled.KeyboardArrowLeft,
                stringResource(feature_home_decrement_date)
            )
        }
        Text(
            date.value.toDateString(),
            Modifier.padding(horizontal = PaddingL.toDp())
        )
        DateWidgetButton(onClick = { date.value = MistriappDate(day, year).getNextDate()}) {
            Icon(
                AutoMirrored.Filled.KeyboardArrowRight,
                stringResource(feature_home_increment_date)
            )
        }
    }
}

@Composable
private fun DefaultSelectedDateText() =
    Text(stringResource(feature_home_selected_date), Modifier.padding(horizontal = PaddingL.toDp()))

@Composable
fun DateWidgetButton(onClick: () -> Unit, content: @Composable () -> Unit) =
    Button(
        onClick,
        shape = RoundedCornerShape(PaddingL.toDp()),
        colors = filledTonalButtonColors()
    ) { content() }


@Composable
fun HomeViewModel.ProfileCard(homeUiState: HomeInfo) = Card(Modifier.fillMaxWidth()) {
    val name = homeUiState.selectedProfile.name
    InfoItem(name = name, icon = Filled.AccountCircle).ToInfoCard(false)

}

@Composable
fun RemindersSection() = InfoBlock(
    modifier = Modifier.fillMaxHeight(),
    header = stringResource(id = feature_home_reminders),
    items = listOf(InfoItem(name = stringResource(id = feature_home_empty)))
)
