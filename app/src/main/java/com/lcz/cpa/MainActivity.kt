package com.lcz.cpa

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.view.MenuProvider
import androidx.core.view.WindowCompat
import com.google.accompanist.themeadapter.material.MdcTheme
import com.lcz.cpa.compose.home.SunFlowerPage
import com.lcz.cpa.ui.theme.LczMvvmComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val menuProvider = object : MenuProvider {
        override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
            menuInflater.inflate(R.menu.menu_plant_list, menu)
        }

        override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
            return when (menuItem.itemId) {
                R.id.filter_zone -> {
//                    viewModel.updateData()
                    true
                }
                else -> false
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // This app draws behind the system bars, so we want to handle fitting system windows
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            MdcTheme{
                SunflowerApp(
                    onAttached = { toolbar ->
                        setSupportActionBar(toolbar)
                    }, onPageChange = { page ->
                        when (page) {
                            SunFlowerPage.MY_GARDEN -> removeMenuProvider(menuProvider)
                            SunFlowerPage.PLANT_LIST -> addMenuProvider(
                                menuProvider,
                                this
                            )
                        }
                    }
                )

            }
        }
    }
}