package com.example.scratchcard.presentation

import com.example.scratchcard.model.ScratchCardModel
import com.example.scratchcard.model.ScratchCardModel.*

object ScratchCardModelConverter {
    fun ScratchCardModel.toState(): ScratchCardState = when (this) {
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
            code = "",
            activated = false
        )
    }
}
