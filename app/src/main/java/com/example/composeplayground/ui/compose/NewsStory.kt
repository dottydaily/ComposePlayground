package com.example.composeplayground.ui.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composeplayground.R

@Composable
fun NewsStory(toastFunction: ((Int?) -> Unit)? = null) {
    MaterialTheme {
        val typography = MaterialTheme.typography
        toastFunction?.invoke(null)
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.zpell),
                contentDescription = null,
                modifier = Modifier
                    .height(320.dp)
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Column(
                modifier = Modifier.padding(end = 32.dp, bottom = 32.dp)
            ) {
                Text("A day travelling to Thanyaburi." +
                        "\nAt the biggest mall in the city, " +
                        "\"Zpell - Future Park Rangsit\"",
                    style = typography.h6,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                var clickCount by rememberSaveable { mutableStateOf(0) }
                if (clickCount > 5) {
                    Text("Pathum Thani, Thailand", style = typography.body2)
                    Text("April 26, 2021", style = typography.body2)
                }
                ClickCounter(toastFunction, clickCount) {
                    clickCount += 1
                }
            }
        }
    }
}

@Composable
fun ClickCounter(toastFunction: ((Int) -> Unit)?, clicks: Int, onClick: () -> Unit) {
//    toastFunction?.invoke(clicks)
    Button(onClick = onClick) {
        Text("I've been clicked $clicks times")
    }
}