package com.sparrow.ux_material_3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TriStateCheckbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sparrow.ux_material_3.ui.theme.UXMaterial3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UXMaterial3Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    App()
                }
            }
        }
    }
}


data class ToggleableInfo(
    val isChecked: Boolean, val text: String
)

@Composable
fun Checkboxes() {
    val checkboxes = remember {
        mutableStateListOf(
            ToggleableInfo(
                isChecked = false, text = "Photos"
            ), ToggleableInfo(
                isChecked = false, text = "Videos"
            ), ToggleableInfo(
                isChecked = false, text = "Audio"
            )
        )
    }

    var triState by remember {
        mutableStateOf(ToggleableState.Indeterminate)
    }

    val toggleTriState = {
        triState = when (triState) {
            ToggleableState.Indeterminate -> ToggleableState.On
            ToggleableState.On -> ToggleableState.Off
            else -> ToggleableState.On
        }
        checkboxes.indices.forEach { index ->
            checkboxes[index] = checkboxes[index].copy(
                isChecked = triState == ToggleableState.On
            )
        }

    }

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        TriStateCheckbox(state = triState, onClick = {
            toggleTriState()
        })
        Text("File Types")
    }

    Column {
        checkboxes.forEachIndexed { index, info ->

            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = info.isChecked, onCheckedChange = { isChecked ->
                    checkboxes[index] = info.copy(
                        isChecked = isChecked
                    )
                    triState = ToggleableState.Indeterminate
                })
                Text(info.text)
            }

        }
    }
}


@Composable
fun App() {
    val scrollState = rememberScrollState()         // Initialize a scroll state value
    var isError by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(state = scrollState),       // Add this modifier to enable scrolling
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Text(
            "UX Material 3 Design", fontSize = 32.sp, fontWeight = FontWeight.ExtraLight
        )
        Spacer(Modifier.height(64.dp))


        // Selection UI Components

        Text(
            "Selection UI Components", fontSize = 28.sp, fontWeight = FontWeight.ExtraLight
        )
        Spacer(Modifier.height(32.dp))

        Checkboxes()        // Composable defined separately


        // Buttons

        Spacer(Modifier.height(32.dp))
        Text(
            "Buttons", fontSize = 28.sp, fontWeight = FontWeight.ExtraLight
        )

        Spacer(Modifier.height(16.dp))



        Button(onClick = {}) {
            Text("Subscribe")
        }

        ElevatedButton(onClick = {}) {
            Icon(
                imageVector = Icons.Outlined.Add,
                contentDescription = "",
                modifier = Modifier.size(18.dp)
            )
            Spacer(Modifier.width(8.dp))
            Text("Add")

        }

        FilledTonalButton(onClick = {}) {
            Text("Open in Browser")
        }


        OutlinedButton(onClick = {
            isError = !isError
        }) {
            Text("Toggle Error")
        }

        TextButton(onClick = {}) {
            Text("Learn More")
        }
        Spacer(Modifier.height(32.dp))


        // Text Fields

        Text(
            "Text Fields", fontSize = 28.sp, fontWeight = FontWeight.ExtraLight
        )
        Spacer(Modifier.height(16.dp))

        var filledText by remember {
            mutableStateOf("")
        }


        TextField(value = filledText, onValueChange = { filledText = it }, label = {
            Text("Label")
        }, placeholder = {
            Text("Placeholder")
        }, leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Search, contentDescription = null
            )
        }, trailingIcon = {
            Icon(
                imageVector = Icons.Outlined.Lock, contentDescription = null
            )
        }, prefix = {
            Text("$ ")
        }, suffix = {
            Text(" kg")
        }, supportingText = {
            Text("*required")
        }, isError = isError

        )

        Spacer(Modifier.height(16.dp))
        var password by remember { mutableStateOf("") }
        TextField(value = password, onValueChange = { password = it }, placeholder = {
            Text("Password")
        }, visualTransformation = PasswordVisualTransformation(), singleLine = true
        )

        Spacer(Modifier.height(16.dp))
        var num by remember { mutableStateOf("") }
        TextField(
            value = num,
            onValueChange = { num = it },
            placeholder = {
                Text("Enter a number")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number, imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(onNext = {
                println("hello world")
            }),
        )
        Spacer(Modifier.height(16.dp))


    }

}