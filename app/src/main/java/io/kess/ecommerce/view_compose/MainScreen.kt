package io.kess.ecommerce.view_compose

import androidx.compose.material3.*

import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import androidx.compose.runtime.Composable


@Composable
fun MainScreen() {
    Scaffold(
        topBar = { AppBar() }, bottomBar = { BottomBar() }
    ) {padding ->
        HomeContent(
            modifier = Modifier.padding(padding)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun MainScreenPreview(){
    MainScreen()
}