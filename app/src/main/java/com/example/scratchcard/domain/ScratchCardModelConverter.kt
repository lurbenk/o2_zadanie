package com.example.scratchcard.domain

import com.example.scratchcard.domain.ScratchCardModel
import com.example.scratchcard.domain.ScratchCardModel.*
import com.example.scratchcard.presentation.ScratchCardState

object ScratchCardModelConverter {
    fun ScratchCardModel.toState(): ScratchCardState =
        when (this) {
            is Activated -> ScratchCardState(
                scratched = true,
                code = this.code,
                activated = true
            )

            is Scratched -> ScratchCardState(
                scratched = true,
                code = this.code,
                activated = false
            )

            Unscratched -> ScratchCardState(
                scratched = false,
                code = null,
                activated = false
            )
        }
}