package com.example.testafterwhile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.io.PrintStream
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mainViewModel = MainViewModel()
        setContent {
            /* val fontFamily = FontFamily(
                 Font(resId = R.font.roboto_bold, weight = FontWeight.Bold),
                 Font(resId = R.font.roboto_medium, weight = FontWeight.Medium),
                 Font(resId = R.font.roboto_regular, weight = FontWeight.Normal),
             )
             val painter = painterResource(id = R.drawable.ic_karmit)
             val description = "Kermit playing in the snow"
             val title = "Lorem ipsum dolor sit amet"*/

            // ImageCard(painter, description, title, fontFamily)

            val scaffoldState = rememberScrollState()


            TestTextFields(mainViewModel)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TestTextFields(mainViewModel: MainViewModel) {
    var testFieldState by remember {
        mutableStateOf("")
    }
    val name = mainViewModel.name.collectAsState()

    Scaffold(modifier = Modifier.fillMaxSize()) { it ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            // .padding(it),
            // verticalArrangement = Arrangement.spacedBy(16.dp),

        ) {

            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 16.dp),
                value = testFieldState,
                placeholder = {
                    Text(text = "text")
                },
                onValueChange = { text ->
                    testFieldState = text
                })

            OutlinedTextField(
                value = name.value,
                onValueChange = mainViewModel::setName,
                label = {
                    Text(
                        text = "Email"
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .padding(top = 10.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = Color.Blue,
                    textColor = Color.Green
                ),
                singleLine = true,

                shape = RoundedCornerShape(12.dp),
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { print(name.value) }) {
                Text(text = "Click me")
            }

        }
    }
}

@Composable
fun ImageCard(
    painter: Painter,
    contentDescription: String,
    title: String,
    fontFamily: FontFamily,
    modifier: Modifier = Modifier
) {
    val color = remember {
        mutableStateOf(Color.Yellow)
    }

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp,
        ),
    ) {
        Box(modifier = Modifier.height(200.dp)) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painter,
                contentDescription = contentDescription,
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                color.value
                            ),
                            startY = 300f
                        )
                    )
                    .clickable {
                        color.value = Color(
                            Random.nextFloat(),
                            Random.nextFloat(),
                            Random.nextFloat(),
                            1f
                        )
                    }
            )
            Text(
                modifier = Modifier.align(alignment = Alignment.BottomStart),
                text = title, style = TextStyle(
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Center,
                ),
                fontFamily = fontFamily
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldExample() {
    var presses by remember {
        mutableStateOf(0)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Top app bar")
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary,
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = "Bottom app bar",
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { presses++ }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                text =
                """
                    This is an example of a scaffold. It uses the Scaffold composable's parameters to create a screen with a simple top app bar, bottom app bar, and floating action button.

                    It also contains some basic inner content, such as this text.

                    You have pressed the floating action button $presses times.
                """.trimIndent(),
            )
        }
    }
}



