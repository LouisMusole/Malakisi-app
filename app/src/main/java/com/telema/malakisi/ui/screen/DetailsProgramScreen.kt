package com.telema.malakisi.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.telema.malakisi.domain.model.Program
import com.telema.malakisi.domain.ui.DetailsProgramTabs
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailsProgramScreen(
    program: Program,
    onBack: () -> Unit
){

    val viewModel : DetailsProgramViewModel = koinViewModel()

    val agendastate = viewModel.agendas.collectAsState()
    val learnersstate = viewModel.learners.collectAsState()
    val instatestate = viewModel.instructors.collectAsState()

    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = { DetailsProgramTabs.entries.size })
    val selectedTabIndex = remember { derivedStateOf { pagerState.currentPage } }

    LaunchedEffect(Unit) {
        viewModel.getDetailsProgram(program)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
    ){
        Box(modifier = Modifier.fillMaxWidth()){
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(program.coverimageurl).build(),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.FillWidth
            )
            IconButton(
                onClick = { onBack()},
                modifier = Modifier.padding(top = 32.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null,
                    tint = Color.White,
                )
            }
            Column(modifier = Modifier
                .padding(16.dp)
                .align(Alignment.BottomStart)) {
                Row(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.tertiaryContainer)
                        .padding(8.dp)
                ) {
                    Text(text = "${program.duration} Mois", color = MaterialTheme.colorScheme.onTertiaryContainer)
                }

                Text(
                    text = program.name,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.ExtraBold,color = Color.White)
            }
        }

        TabRow(
            selectedTabIndex = selectedTabIndex.value,
            modifier = Modifier.fillMaxWidth()
        ) {
            DetailsProgramTabs.entries.forEachIndexed { index, currentTab ->
                Tab(
                    selected = selectedTabIndex.value == index,
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(currentTab.ordinal)
                        }
                    },
                    text = { Text(text = currentTab.text)}
                )
            }
        }

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Box(modifier = Modifier
                .fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                when (DetailsProgramTabs.entries[selectedTabIndex.value]) {
                    DetailsProgramTabs.AgendaTab ->  AgendaTabScreen(agendas = agendastate.value)
                    DetailsProgramTabs.LearnersTab -> LearnersTabScreen(learners = learnersstate.value)
                    DetailsProgramTabs.InfoTab -> InfoProgramTabScreen(program = program, instructors = instatestate.value)
                }
            }
        }
    }
}