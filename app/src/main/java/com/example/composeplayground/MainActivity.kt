package com.example.composeplayground

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {

    // ViewModel
    private val viewModel by viewModels<MainViewModel>()

    // Toast the text.
    private val toastMessage: () -> Unit =  {
        Toast.makeText(this, "Clicked.", Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Hello()
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
fun Hello(helloViewModel: MainViewModel = viewModel()) {
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
    Row(modifier = Modifier.padding(16.dp)) {
        Button(onClick = onSubmitClick) {
            Text(text = "Submit")
        }
        Text(text = fullName,
            modifier = Modifier
                .padding(start = 16.dp)
                .fillMaxHeight()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Hello()
}