package com.telema.malakisi.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun InfoScreen() {
    Box (modifier = Modifier.fillMaxSize()){
        Column(modifier =Modifier.padding(16.dp)) {
            Text(text = "Malakisi", fontWeight = FontWeight.ExtraBold, fontSize = 30.sp)

            Spacer(modifier = Modifier.height(12.dp))

            Text(text = "Malakisi est un programme de formation qui a pour obhectif général de sensibiliser, " +
                    "motiver et susciter " +
                    "l'intérêt pour la transformation numérique parmi les parties prenantes qui comprennent " +
                    "les véritables problèmes locaux qui doivent être résolus. D’ici le 15 décembre 2024, " +
                    "notre objectif est de former 200 jeunes étudiants (âgés de 18 à 25 ans) des deux " +
                    "universités congolaises de la ville de Bukavu aux compétences modernes en matière " +
                    "de développement de logiciels.\n" +
                    "Au moins 80 % des étudiants participants obtiendront des certificats reconnus " +
                    "par l'industrie technologique, et 60 % d'entre eux contribueront à des projets " +
                    "de logiciels open source communautaires.",
                lineHeight = 30.sp,)
        }

    }
}
