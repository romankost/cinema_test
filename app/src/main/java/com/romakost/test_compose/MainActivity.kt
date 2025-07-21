package com.romakost.test_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.romakost.core.navigation.EntryProviderInstaller
import com.romakost.core.navigation.Navigator
import com.romakost.core.ui.theme.CinemaAppTheme
import com.romakost.home.presentation.Home
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigator: Navigator

    @Inject
    lateinit var entryProviderBuilders: Set<@JvmSuppressWildcards EntryProviderInstaller>

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        setContent {
            CinemaAppTheme {
                Home(navigator = navigator, entryProviderBuilders = entryProviderBuilders)
            }
        }
    }
}
