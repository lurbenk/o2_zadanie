package com.example.scratchcard.presentation

object ResponseValidator {
    fun isValid(input: String?): Boolean =
        try {
            val inputNumber = input?.toLongOrNull()
            if(inputNumber != null) inputNumber > VALIDATION_VALUE
            else false
        } catch (e: Exception) {
            false
    }
}

private const val VALIDATION_VALUE = 277028