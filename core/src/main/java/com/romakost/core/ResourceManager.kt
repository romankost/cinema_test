package com.romakost.core

import android.content.Context
import androidx.annotation.StringRes
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class ResourceManager @Inject constructor(
    @ApplicationContext
    private val ctx: Context
) {
    fun getString(@StringRes id: Int) = ctx.getString(id)
}
