package com.thedullpencil.core.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp
import com.thedullpencil.core.ui.R.dimen

enum class Dimens(val id: Int) {
    PaddingXXS(dimen.core_ui_padding_xxs),
    PaddingXS(dimen.core_ui_padding_xs),
    PaddingS(dimen.core_ui_padding_s),
    PaddingM(dimen.core_ui_padding_m),
    PaddingL(dimen.core_ui_padding_l),
    PaddingXL(dimen.core_ui_padding_xl),
    PaddingXXL(dimen.core_ui_padding_xxl),
}

@Composable
fun Dimens.toDp() : Dp = dimensionResource(this.id)