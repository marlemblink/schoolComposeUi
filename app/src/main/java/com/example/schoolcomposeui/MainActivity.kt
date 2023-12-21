package com.example.schoolcomposeui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.schoolcomposeui.ui.theme.SchoolComposeUiTheme
import com.example.schoolcomposeui.vm.MainVM
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SchoolComposeUiTheme {
                val mainViewModel = getViewModel<MainVM>()
               homeScreen(mainViewModel)
            }
        }
    }
}

//@Preview(showBackground = true)
@Composable
fun homeScreen(mainViewModel: MainVM) {
    val cardClickedState = remember {
        mutableStateOf(false)
    }
    val dataSchool = mainViewModel.dataList
    Log.d("******", "call: ${dataSchool}")
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
                   .clickable {
                       cardClickedState.value = cardClickedState.value.not()
                   },
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
            if(cardClickedState.value) openDetails(item.dbn)
        }
    }
}

@Composable
fun openDetails(id: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(10.dp)
    ) {
        Card(
            modifier = Modifier
                .padding(15.dp)
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .background(Color.Cyan)
                    .padding(10.dp)
                    .align(alignment = Alignment.CenterHorizontally)
            ) {
                Text(text = "details: $id")
            }
        }
    }
}