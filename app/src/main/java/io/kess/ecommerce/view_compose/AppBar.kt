package io.kess.ecommerce.view_compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.kess.ecommerce.R
import io.kess.ecommerce.view_compose.theme.Manrope
import io.kess.ecommerce.view_compose.theme.Primary
import io.kess.ecommerce.view_compose.theme.TextSecondary

@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar() {
    TopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(Color.Blue)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.img_profile_avata),
                        contentDescription = "profile",
                        modifier = Modifier.matchParentSize()
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Column(   modifier = Modifier.weight(1f),
                    ) {
                    Text(
                        text = "Hi, Jonathan",
                        fontFamily = Manrope,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                                lineHeight = 18.sp
                    )
                    Text(
                        text = "Let's go shopping",
                        fontSize = 8.sp,
                        color = TextSecondary,
                        fontFamily = Manrope,
                        fontWeight = FontWeight.Normal,
                        lineHeight = 12.sp
                    )
                }


                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search"
                    )
                }
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Default.Notifications,
                        contentDescription = "notification"
                    )
                }
            }
        }
    )
}