package com.telema.malakisi.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.telema.malakisi.domain.model.Agenda

@Composable
fun AgendaTabScreen(agendas : List<Agenda>){

    val agendasGrouped = agendas.groupBy { it.week }

    Box(modifier = Modifier.padding(12.dp)){
        LazyColumn(modifier = Modifier.fillMaxSize()){
            agendasGrouped.forEach { (week, agendas) ->
                item {
                    Row(
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.surfaceContainer)
                            .fillParentMaxWidth()
                            .padding( 8.dp)) {
                        Text(
                            text = week,
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
                items(agendas){
                    Row(
                        modifier = Modifier
                            .fillParentMaxWidth()
                            .padding(vertical = 12.dp)) {
                        Text(
                            text = it.lesson, style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }

                }
            }
        }
    }
}