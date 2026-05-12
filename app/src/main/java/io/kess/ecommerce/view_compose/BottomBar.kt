package io.kess.ecommerce.view_compose

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.test.espresso.base.Default

@Preview(showBackground = true)
@Composable
fun BottomBar() {
    NavigationBar {
        NavigationBarItem(
            selected = true,
            onClick = { },
            icon = { Icon(
                imageVector = Icons.Default.Home,
                contentDescription = "Search"
            ) },
            label = { Text("Home") }
        )

        NavigationBarItem(
            selected = false,
            onClick = { },
            icon = { Text("🛒") },
            label = { Text("Cart") }
        )

        NavigationBarItem(
            selected = false,
            onClick = { },
            icon = { Text("👤") },
            label = { Text("Profile") }
        )
    }
}