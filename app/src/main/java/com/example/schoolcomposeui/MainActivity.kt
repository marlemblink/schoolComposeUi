package com.example.schoolcomposeui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.schoolcomposeui.ui.theme.SchoolComposeUiTheme
import com.example.schoolcomposeui.vm.MainVM
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SchoolComposeUiTheme {
                val mainViewModel = getViewModel<MainVM>()
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "home_screen") {
                    composable(route = "home_screen") {
                        HomeScreen(mainViewModel = mainViewModel, navController = navController)
                    }
                    composable(route = "detail_screen/{id}",
                        arguments = listOf(navArgument("id") {
                            type = NavType.StringType
                            defaultValue = ""
                        })
                    ) { entry->
                        mainViewModel.getSchoolDetailList()
                        entry.arguments?.let {
                            DetailsScreen(
                                mainViewModel = mainViewModel,
                                navController = navController,
                                it.getString("id") ?: ""
                            )
                        }
                    }
                }
            }
        }
    }
}

//@Preview(showBackground = true)
@Composable
fun HomeScreen(mainViewModel: MainVM, navController: NavController) {
    val dataSchool = mainViewModel.dataList
    Column(
        modifier = Modifier
            .padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Harvard campus",
            fontWeight = FontWeight.Bold, fontSize = 20.sp
        )
        Spacer(modifier = Modifier.padding(6.dp))
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            itemsIndexed(items = dataSchool) { index, item ->
                Card(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxSize()
                        .background(Color.LightGray)
                        .clickable(
                            interactionSource = MutableInteractionSource(),
                            indication = null,
                            onClick = {
                                navController.navigate(("detail_screen/${item.dbn}"))
                            }
                        ),
                    shape = RectangleShape,
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 4.dp
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .padding(10.dp),
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Text(text = "School: " + item.school_name, fontWeight = FontWeight.Bold)
                        Text(text = "DBN: " + item.dbn)
                        Text(text = "City: " + item.city)
                    }
                }
            }
        }
    }
}

@Composable
private fun DetailsScreen(mainViewModel: MainVM, navController: NavController, id: String) {
    val filterDetail = mainViewModel.dataDetailList.filter { it.dbn == id.trim() }
    Column(
        modifier = Modifier
            .padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Campus detail",
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            fontWeight = FontWeight.Bold, fontSize = 20.sp
        )
        Spacer(modifier = Modifier.padding(6.dp))
        if (id.isNullOrEmpty().not() && filterDetail.isNullOrEmpty().not()) {
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceAround
            ) {
                itemsIndexed(items = filterDetail) { index, item ->
                    Card(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxSize()
                            .background(Color.LightGray),
                        shape = RectangleShape,
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 4.dp
                        )
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(10.dp),
                            verticalArrangement = Arrangement.Center,
                        ) {
                            Text(text = "School: " + item.school_name, fontWeight = FontWeight.Bold)
                            Text(text = "DBN: " + item.dbn)
                            Text(text = "Reading raking: " + item.sat_critical_reading_avg_score)
                            Text(text = "Math average: " + item.sat_math_avg_score)
                        }
                    }
                }
            }
        } else {
            Column(
                modifier = Modifier
                    .padding(10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "No data, choose other campus",
                    modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                    fontWeight = FontWeight.Bold, fontSize = 20.sp
                )
                Spacer(modifier = Modifier.padding(15.dp))
            }
        }
        Button(
            modifier = Modifier
                .align(Alignment.End),
            onClick = {
            navController.popBackStack()
        }) {
            Text(text = "Back")
        }
    }
}