package com.example.composeplayground.ui.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composeplayground.MainViewModel

@Composable
fun Greeting(helloViewModel: MainViewModel = viewModel()) {
    Column {
        HelloFirstname(helloViewModel)
        HelloLastname(helloViewModel)
        HelloResult(helloViewModel)
    }
}

@Composable
fun HelloFirstname(viewModel: MainViewModel) {
    val name by viewModel.firstName.observeAsState("")

    HelloContent(label = "First", name = name, onNameChange = {
        viewModel.onFirstNameChange(it)
    })
}

@Composable
fun HelloLastname(viewModel: MainViewModel) {
    val name by viewModel.lastName.observeAsState(  "")

    HelloContent(label = "Last", name = name, onNameChange = {
        viewModel.onLastNameChange(it)
    })
}

@Composable
fun HelloContent(label: String, name: String, onNameChange: (String) -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {
        if (name.isNotEmpty()) {
            Text(
                text = "Hello, $name",
                modifier = Modifier.padding(bottom = 8.dp),
                style = MaterialTheme.typography.h5
            )
        }
        OutlinedTextField(
            value = name,
            onValueChange = onNameChange,
            label = { Text(label) }
        )
    }
}

@Composable
fun HelloResult(viewModel: MainViewModel) {
    val name: String by viewModel.fullName.observeAsState("-")

    HelloSubmit(name, onSubmitClick = {
        viewModel.onFullNameSubmit()
    })
}

@Composable
fun HelloSubmit(fullName: String, onSubmitClick: () -> Unit) {
    val formattedText = remember(fullName) {
        ">>>>> $fullName <<<<<"
    }
    Row(modifier = Modifier.padding(16.dp)) {
        Button(onClick = onSubmitClick) {
            Text(text = "Submit")
        }
        if (fullName.isNotEmpty()) {
            Text(text = formattedText,
                modifier = Modifier
                    .padding(start = 16.dp)
                    .fillMaxHeight()
            )
        }
    }
}