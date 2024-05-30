package com.telema.malakisi.ui.screen

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.telema.malakisi.domain.model.Program
import com.telema.malakisi.domain.ui.DetailsProgram
import com.telema.malakisi.domain.ui.Home


@Composable
fun MalakisiAppScreen(
    isDarkMode: Boolean,
    onThemeChange: (Boolean) -> Unit
){
    val navContoller = rememberNavController()

    NavHost(
        navController = navContoller,
        startDestination = Home,
        enterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(700)) },
        exitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(700)) },
        popEnterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(700)) },
        popExitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(700)) }
    ){
       composable<Home> {
           HomeScreen(
               isDarkMode = isDarkMode,
               onThemeChange = onThemeChange,
               onSelectProgram = {
                   navContoller.navigate(
                       DetailsProgram(
                           it.id, it.name, it.description, it.coverimageurl, it.duration
                       )
                   )
               }
           )
       }
        composable<DetailsProgram> {
            val program = Program(
                it.toRoute<DetailsProgram>().id,
                it.toRoute<DetailsProgram>().name,
                it.toRoute<DetailsProgram>().description,
                it.toRoute<DetailsProgram>().coverimageurl,
                it.toRoute<DetailsProgram>().duration,
            )
            DetailsProgramScreen(program = program, onBack = {navContoller.popBackStack()})
        }
    }

}

