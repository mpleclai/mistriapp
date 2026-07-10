package com.thedullpencil.data.util

import android.content.Context
import androidx.annotation.RawRes
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.serialization.json.Json
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Singleton helper that loads and decodes a raw JSON resource into [T].
 *
 * Centralises the [Context] and [Json] configuration so repositories
 * don't need to depend on either directly.
 */
@Singleton
class JsonResourceLoader @Inject constructor(
    @ApplicationContext context: Context,
) {
    // @PublishedApi internal so the inline decode function can access them
    // without requiring synthetic accessor methods.
    @PublishedApi internal val context: Context = context
    @PublishedApi internal val json: Json = Json { ignoreUnknownKeys = true }

    inline fun <reified T> decode(@RawRes rawResId: Int): T =
        json.decodeFromRawResource(context, rawResId)
}

