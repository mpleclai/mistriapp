package com.thedullpencil.data.util

import android.content.Context
import androidx.annotation.RawRes
import kotlinx.serialization.json.Json

/**
 * Reads a raw JSON resource and decodes it into [T].
 *
 * @param context  Application context used to open the raw resource.
 * @param rawResId The `R.raw.*` resource ID of the JSON file.
 */
inline fun <reified T> Json.decodeFromRawResource(context: Context, @RawRes rawResId: Int): T {
    val jsonString = context.resources.openRawResource(rawResId)
        .bufferedReader()
        .use { it.readText() }
    return decodeFromString(jsonString)
}

