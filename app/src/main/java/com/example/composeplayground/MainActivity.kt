package com.example.composeplayground

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composeplayground.ui.compose.Greeting

class MainActivity : ComponentActivity() {

    // ViewModel
//    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExpandableCard()
//            ComposePlaygroundTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(color = MaterialTheme.colors.background) {
//                    Greeting("Android")
//                }
//            }
        }
    }
}

@Composable
fun ExpandableCard() {
    ExpandingCard(title = "ExpandableCard", body = "This is the card body")
}

@Composable
fun ExpandingCard(title: String, body: String) {
    var expanded by remember { mutableStateOf(false) }

    // describe the card for the current state of expanded
    Card {
        Column(
            Modifier
                .width(280.dp)
                .animateContentSize() // automatically animate size when it changes
                .padding(top = 16.dp, start = 16.dp, end = 16.dp)
        ) {
            Text(text = title)

            // content of the card depends on the current value of expanded
            if (expanded) {
                Text(text = body, Modifier.padding(top = 8.dp))
                // change expanded in response to click events
                IconButton(
                    onClick = { expanded = false },
                    modifier = Modifier
                        .size(16.dp)
                        .align(Alignment.CenterHorizontally)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_collapse_arrow),
                        contentDescription = "Expand less",
                        modifier = Modifier.padding(4.dp)
                    )
                }
            } else {
                // change expanded in response to click events
                IconButton(
                    onClick = { expanded = true },
                    modifier = Modifier
                        .size(16.dp)
                        .align(Alignment.CenterHorizontally)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_expand_arrow),
                        contentDescription = "Expand more",
                        modifier = Modifier.padding(4.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ExpandableCard()
}