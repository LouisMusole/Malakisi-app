package com.telema.malakisi.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.telema.malakisi.ui.screen.MalakisiAppScreen
import com.telema.malakisi.ui.theme.MalakisiTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {

            val viewModel: MainActivityViewModel by viewModel()
            val isLoading = viewModel.isLoading.collectAsState()

            splashScreen.setKeepOnScreenCondition{
                isLoading.value
            }

            val systemDarkMode = isSystemInDarkTheme()
            var isDarkMode by rememberSaveable { mutableStateOf(systemDarkMode) }

            MalakisiTheme(darkTheme = isDarkMode) {
                MalakisiAppScreen(
                    isDarkMode = isDarkMode,
                    onThemeChange = { isDarkMode = it }
                )
            }
        }
    }
}