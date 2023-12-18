package com.example.ambatik.ui.components.leaderboard

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.ambatik.ui.theme.AmbatikTheme

@Composable
fun LeaderboardItem(
    name: String,
    point: String,
    urlProfile: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
    ) {
        AsyncImage(
            model = urlProfile,
            contentDescription = "IMAGE LEADERBOARD",
            contentScale = ContentScale.Crop,
            modifier = modifier
                .size(75.dp)
                .clip(CircleShape)
        )
        Box(
            modifier = modifier
                .padding(start = 12.dp)
                .fillMaxWidth()
                .height(75.dp),
            contentAlignment = Alignment.CenterStart
        ){
            Column {
                Text(
                    text = name,
                )
                Text(
                    text = "Total Poin: $point"
                )
            }
        }
    }
}

@Composable
@Preview
fun PreviewLeaderboardItem(){
    AmbatikTheme {
        LeaderboardItem(
            name = "",
            point = "",
            urlProfile = "",
        )
    }
}