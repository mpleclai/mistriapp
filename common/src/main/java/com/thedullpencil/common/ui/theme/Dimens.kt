package com.thedullpencil.common.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp
import com.thedullpencil.common.R.*

enum class Dimens(val id: Int) {
    PaddingXXS(dimen.padding_xxs),
    PaddingXS(dimen.padding_xs),
    PaddingS(dimen.padding_s),
    PaddingM(dimen.padding_m),
    PaddingL(dimen.padding_l),
    PaddingXL(dimen.padding_xl),
    PaddingXXL(dimen.padding_xxl),
}

@Composable
fun Dimens.toDp() : Dp = dimensionResource(this.id)