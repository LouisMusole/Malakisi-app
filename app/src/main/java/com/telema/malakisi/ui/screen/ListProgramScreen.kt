package com.telema.malakisi.ui.screen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.telema.malakisi.domain.model.Program
import org.koin.androidx.compose.koinViewModel

@Composable
fun ListProgramScreen(
    onSelectProgram : (Program) -> Unit
) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ){
        val viewModel : ListProgramScreenViewModel = koinViewModel()
        val programsState = viewModel.programs.collectAsState()
        Text(
            text = "Les programmes d'apprentissage disponnible pour cette cohorte",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            style = TextStyle(
                lineHeight = 40.sp
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        val pagerState = rememberPagerState(
            pageCount = {programsState.value.size},
        )

        HorizontalPager(
            modifier = Modifier
                .fillMaxSize(),
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 60.dp, vertical = 40.dp),
            pageContent = {index->
                val pagerOffset = (pagerState.currentPage) - index + pagerState.currentPageOffsetFraction
                val imageSize by animateFloatAsState(
                    targetValue = if (pagerOffset  != 0.0f) 0.8f else 1f,
                    animationSpec = tween(300), label = ""
                )
                Card(
                    onClick = {
                         onSelectProgram(programsState.value[index])
                    },
                    modifier = Modifier.graphicsLayer {
                    scaleX = imageSize
                    scaleY = imageSize
                }
                ){
                    Box{
                        AsyncImage(
                            modifier = Modifier
                                .fillMaxHeight()
                                .clip(RoundedCornerShape(16.dp))
                                ,
                            model =ImageRequest.Builder(LocalContext.current)
                                .data(programsState.value[index].coverimageurl).build(),
                            contentDescription = null,
                            contentScale = ContentScale.Crop
                        )
                        Column(modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.BottomCenter)
                            .height(200.dp)
                            .clip(RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp))
                            .background(
                                brush = Brush.verticalGradient(
                                    colors = listOf(
                                        Color.Transparent,
                                        Color.Black
                                    )
                                )
                            )
                            .graphicsLayer {
                                scaleX = imageSize
                                scaleY = imageSize
                            }
                            .padding(16.dp)
                        ) {
                            Text(
                                text = programsState.value[index].name,
                                fontSize = 32.sp,
                                fontWeight = FontWeight.ExtraBold,color = Color.White)
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = programsState.value[index].description, color = Color.White,
                                overflow = TextOverflow.Ellipsis
                            )
                        }

                        Row(
                            modifier = Modifier
                                .padding(20.dp)
                                .background(MaterialTheme.colorScheme.tertiaryContainer)
                                .padding(8.dp)
                        ) {
                            Text(text = "${programsState.value[index].duration} Mois", color = MaterialTheme.colorScheme.onTertiaryContainer)
                        }
                    }
                }
            }
        )
    }
}
