package com.thedullpencil.home

import androidx.compose.foundation.layout.Arrangement.SpaceEvenly
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons.AutoMirrored
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.filledTonalButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.thedullpencil.core.ui.theme.Dimens.PaddingL
import com.thedullpencil.core.ui.theme.toDp
import com.thedullpencil.core.util.Day
import com.thedullpencil.core.util.MistriappDate
import com.thedullpencil.core.util.getNextDate
import com.thedullpencil.core.util.getPreviousDate
import com.thedullpencil.core.util.toDateString
import com.thedullpencil.feature.home.R.string.feature_home_decrement_date
import com.thedullpencil.feature.home.R.string.feature_home_increment_date
import com.thedullpencil.feature.home.R.string.feature_home_selected_date

@Composable
fun DateWidget(currentDate: Day, currentYear: Int) = Row(
    Modifier
        .fillMaxWidth()
        .padding(PaddingL.toDp()),
    horizontalArrangement = SpaceEvenly,
    verticalAlignment = CenterVertically
) {
    val date = remember { mutableStateOf(MistriappDate(currentDate, currentYear)) }
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
        DateWidgetButton(onClick = { date.value = MistriappDate(day, year).getNextDate() }) {
            Icon(
                AutoMirrored.Filled.KeyboardArrowRight,
                stringResource(feature_home_increment_date)
            )
        }
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
private fun DefaultSelectedDateText() =
    Text(stringResource(feature_home_selected_date), Modifier.padding(horizontal = PaddingL.toDp()))
