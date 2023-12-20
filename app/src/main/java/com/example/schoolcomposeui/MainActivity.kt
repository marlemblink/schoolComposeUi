package com.example.schoolcomposeui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.schoolcomposeui.ui.theme.SchoolComposeUiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SchoolComposeUiTheme {
               homeScreen()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun homeScreen() {
    val list:List<String> = listOf("name1", "name2", "name3")
    LazyColumn() {
        items(list) {
            Column {
                Text(text = it)
            }
        }
    }
}