package com.example.mynotesshift.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mynotesshift.presentation.theme.MyNotesShiftTheme
import com.example.mynotesshift.presentation.ui.CreateNoteScreen
import com.example.mynotesshift.presentation.ui.MainScreen
import com.example.mynotesshift.presentation.ui.NoteScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            MyNotesShiftTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    NavHost(
                        navController = navController, startDestination = "main_screen"
                    ) {

                        composable("main_screen") {
                            MainScreen(onClick = {
                                navController.navigate("create_screen")
                            }, onClickCard = {
                                navController.navigate("note_screen/${it}")
                            })
                        }

                        composable("note_screen/{id}", arguments = listOf(navArgument("id") {
                            type = NavType.IntType
                            nullable = false
                            defaultValue = 1
                        })) {
                            val id = it.arguments?.getInt("id") ?: 1
                            NoteScreen(id = id, onClickBack = {
                                navController.navigate("main_screen") {
                                    launchSingleTop = true
                                    popUpTo("main_screen") {
                                        inclusive = true
                                    }
                                }
                            }, onSaveClick = {
                                navController.navigate("main_screen") {
                                    launchSingleTop = true
                                    popUpTo("main_screen") {
                                        inclusive = true
                                    }
                                }
                            })
                        }

                        composable("create_screen") {
                            CreateNoteScreen(onClickBack = {
                                navController.navigate("main_screen") {
                                    launchSingleTop = true
                                    popUpTo("main_screen") {
                                        inclusive = true
                                    }
                                }
                            }, onSaveClick = {
                                navController.navigate("main_screen") {
                                    launchSingleTop = true
                                    popUpTo("main_screen") {
                                        inclusive = true
                                    }
                                }
                            })
                        }
                    }
                }
            }
        }
    }
}