package me.ahch.core_ui.paging

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal

/**
 * Returns height of the screen
 *
 * @param percentage is used to get a specific percentage of the total height
 */
@Composable
fun ProvidableCompositionLocal<Configuration>.getHeight(percentage: Int = 100) =
    current.screenHeightDp * percentage / 100