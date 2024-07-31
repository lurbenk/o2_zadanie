package com.example.scratchcard.domain

interface ResourceProvider {
    fun getString(resId: Int): String
}
