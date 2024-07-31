package com.example.scratchcard.system

import android.content.Context
import com.example.scratchcard.domain.ResourceProvider
import javax.inject.Inject

class ResourceProviderImpl @Inject constructor(
    private val context: Context
) : ResourceProvider {
    override fun getString(resId: Int): String {
        return context.getString(resId)
    }
}
