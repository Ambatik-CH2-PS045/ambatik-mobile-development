package com.example.ambatik.ui.components.loading

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun LoadingArticle(
    isLoading: Boolean,
    contentAfterLoading: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    if(isLoading) {
        Column(
            modifier = modifier
        ) {
            Row(
                modifier = modifier
            ) {
                Box(modifier = modifier
                    .padding(12.dp)){
                    Box(
                        modifier = modifier
                            .shimmerEffect()
                            .padding(15.dp, 15.dp, 15.dp, 15.dp)
                            .size(95.dp)
                            .clip(RoundedCornerShape(25.dp))
                    )
                }
                Box(
                    contentAlignment = Alignment.TopCenter,
                    modifier = modifier
                        .padding(0.dp, 15.dp, 15.dp, 0.dp)
                ) {
                    Column {
                        Box(
                            modifier = modifier
                                .height(12.dp)
                                .shimmerEffect()
                                .padding(start = 4.dp, top = 4.dp, end = 4.dp)
                                .fillMaxWidth(4f)
                        )
                        Box(
                            modifier = modifier
                                .padding(0.dp, 30.dp, 0.dp, 0.dp)
                        ) {
                            Row{
                                Box(
                                    modifier = modifier
                                        .height(8.dp)
                                        .shimmerEffect()
                                        .fillMaxWidth(2f)
                                        .padding(5.dp, 0.dp, 0.dp, 0.dp)
                                )
                                Box(
                                    modifier = modifier
                                        .fillMaxWidth(),
                                    contentAlignment = Alignment.CenterEnd
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .height(8.dp)
                                            .shimmerEffect()
                                            .fillMaxWidth(2f)
                                            .padding(5.dp, 0.dp, 0.dp, 0.dp)
                                    )
                                }
                            }
                        }
                    }
                }

            }
        }

    } else {
        contentAfterLoading()
    }
}
