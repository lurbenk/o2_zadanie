package com.example.scratchcard.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.scratchcard.R.drawable
import com.example.scratchcard.ui.VerticalSpace
import com.example.scratchcard.ui.text.LoadingText
import com.example.scratchcard.ui.theme.ScratchCardTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BasicScreenScaffold(
    screenTitle: String,
    loading: String? = null,
    backAction: (() -> Unit)? = null,
    content: @Composable (ColumnScope.() -> Unit)
) {
    ScratchCardTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = screenTitle) },
                    navigationIcon = {
                        backAction?.let {
                            IconButton(
                                onClick = it,
                            ) {
                                Icon(
                                    painter = painterResource(
                                        id = drawable.baseline_arrow_back_24
                                    ),
                                    contentDescription = "",
                                    tint = MaterialTheme.colorScheme.primary,
                                )
                            }
                        } ?: Icon(
                            painter = painterResource(id = drawable.baseline_app_shortcut_24),
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.padding(end = 8.dp)
                        )

                    }
                )
            })
        {
            Box(modifier = Modifier.padding(it)) {
                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()) {
                    content()
                }

            }
            if (loading != null) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.5f))
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        CircularProgressIndicator(
                            color = Color.White,
                            modifier = Modifier.size(48.dp)
                        )
                        VerticalSpace()
                        LoadingText(text = loading)
                    }
                }
            }
        }
    }
}
