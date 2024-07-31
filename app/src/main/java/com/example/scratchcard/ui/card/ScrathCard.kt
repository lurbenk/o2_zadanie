package com.example.scratchcard.ui.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.scratchcard.R.drawable
import com.example.scratchcard.presentation.ScratchCardState
import com.example.scratchcard.ui.text.LargeText

@Composable
fun ScratchCard(state: ScratchCardState) {
    Card() {
        Box() {
            Image(
                painter = painterResource(id = if (state.scratched) drawable.img_scratched else drawable.img_unscratched),
                contentDescription = "",
                contentScale = ContentScale.FillBounds,
            )
            Box(
                Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 45.dp)) {
                LargeText(text = if(state.activated) "Activated" else state.code.orEmpty())
            }
        }
    }
}

@Preview
@Composable
private fun ScratchCardPreview() {
    Column() {
        ScratchCard(state = ScratchCardState(scratched = false, code = null, activated = false))
        ScratchCard(state = ScratchCardState(scratched = true, code = "12345678", activated = false))
        ScratchCard(state = ScratchCardState(scratched = true, code = "12345678", activated = true))
    }
}
