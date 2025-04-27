package com.example.mytodolist

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.compose.MyToDoListTheme
import com.example.mytodolist.logic.ExitDialog
import com.example.mytodolist.screen.NavGraphs
import com.example.mytodolist.screen.destinations.AddScreenDestination
import com.example.mytodolist.screen.destinations.HomeScreenDestination
import com.example.mytodolist.screen.destinations.ListScreenDestination
import com.orhanobut.hawk.Hawk
import com.ramcosta.composedestinations.DestinationsNavHost

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyToDoListTheme {
                val navController = rememberNavController()
                val currentBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = currentBackStackEntry?.destination?.route
                val title = when (currentRoute) {
                    HomeScreenDestination.route -> ""
                    AddScreenDestination.route -> "What To Do Next?"
                    ListScreenDestination.route -> "To Do's"
                    else -> ""
                }
                Scaffold(modifier = Modifier.fillMaxSize(),
                    topBar = {
                        CenterAlignedTopAppBar(title = {
                            Text(
                                title,
                                modifier = Modifier,
                                fontSize = 32.sp,
                                color = MaterialTheme.colorScheme.primary,
                                /*fontFamily = FontFamily(Font(R.font.daphthello))*/
                            )
                        })
                    },
                    bottomBar = {
                        NavigationBar(/*containerColor = MaterialTheme.colorScheme.onBackground*/) {
                            NavigationBarItem(
                                selected = currentRoute == AddScreenDestination.route,
                                onClick = { navController.navigate(AddScreenDestination.route) },
                                icon = { Icon(Icons.Default.Add, "") },
                                label = { null }
                            )
                            NavigationBarItem(
                                selected = currentRoute == HomeScreenDestination.route,
                                onClick = { navController.navigate(HomeScreenDestination.route) },
                                icon = { Icon(Icons.Default.Home, "") },
                                label = { null }
                            )
                            NavigationBarItem(
                                selected = currentRoute == ListScreenDestination.route,
                                onClick = { navController.navigate(ListScreenDestination.route) },
                                icon = { Icon(Icons.Default.Menu, "") },
                                label = { null }
                            )
                        }
                    }) { innerPadding ->
                    val context = LocalContext.current
                    var showExitDialog by remember { mutableStateOf(false) }
                    Hawk.init(context).build()
                    DestinationsNavHost(
                        navGraph = NavGraphs.root, navController = navController,
                        modifier = Modifier.padding(innerPadding)
                    )
                    BackHandler(enabled = true) {
                        showExitDialog = true
                    }
                    if (showExitDialog) {
                        ExitDialog(onDismis = { showExitDialog = false })
                    }
                }
            }
        }
    }
}