package com.udemy.bizcardapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.udemy.bizcardapp.ui.theme.BizCardAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BizCardAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CreateBizCard()

                }
            }
        }
    }
}

// Composable is used to tell android that it is a composable
@Composable
fun CreateBizCard(){
    // variable that holds the state of the button boolean
    val buttonClickedState = remember{
        mutableStateOf(false)
    }
    // Surface is a "Canvas" and passing modifier allows you to change the surface
    Surface (modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
    ) {
        Card(modifier = Modifier
            .width(200.dp)
            .height(390.dp)
            .padding(12.dp),
            // Sets shape of card corners
            shape = RoundedCornerShape(
                corner = CornerSize(15.dp)),
            // Sets color of card
            colors = CardDefaults.cardColors(Color.White),
            // Sets elevation of card
            elevation = CardDefaults.cardElevation(4.dp)
             )
        {
            Column(modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                // Calls composable function for image profile
                ProfileImage()
                Divider()
                CreateInfo()
                Button(
                    onClick = {
                        // makes the click a toggle
                        buttonClickedState.value = !buttonClickedState.value
                        Log.d("Button", "Button Clicked")
                    }
                ) {
                    Text(
                        "Portfolio",
                        style = MaterialTheme.typography.titleLarge)
                }
                // if state is equal to true, show content
                if(buttonClickedState.value){
                    Content()
                }
                // if state is equal to false, hide content
                else{
                    Box{}
                }
            }
        }
    }
}
@Preview
@Composable
private fun Content(){

    Box(modifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth()
        .padding(5.dp)
    ){
        Surface(modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth()
            .fillMaxHeight(),
            shape = RoundedCornerShape(corner = CornerSize(6.dp)),

            border = BorderStroke(width = 2.dp, 
                color = Color.LightGray)
        ) {
            Portfolio(data = listOf(
                "Project 1",
                "Project 2",
                "Project 3",
                "Project 4",
                "Project 5",
                "Project 6"))
        }
    }
}


@Composable
private fun Portfolio(data: List<String>) {
    LazyColumn{
        items(data) { item ->
            // Create cards for each item in lazy column
            Card(modifier = Modifier
                .padding(5.dp)
                .background(MaterialTheme.colorScheme.background)
                .fillMaxWidth(),
                shape = RectangleShape,
                elevation = CardDefaults.cardElevation(1.dp),
                )
            {
                // Sets the content to card
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(1.dp)
                    .background(MaterialTheme.colorScheme.background)
                    .padding(2.dp)
                ) {
                    CreateImageProfile(
                        modifier = Modifier.size(100.dp)
                    )
                    Column( modifier = Modifier
                        .padding(1.dp)
                        .align(alignment = Alignment.CenterVertically)
                    ) {
                        Text(text = item, fontWeight = FontWeight.Bold)
                        Text(text = "A great Project",
                            style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
        }
    }
}

//@Preview
@Composable
private fun CreateImageProfile(modifier: Modifier = Modifier) {
    Surface(modifier = modifier
        .size(150.dp)
        .padding(5.dp),
        shape = CircleShape,
        border = BorderStroke(0.5.dp, Color.LightGray),
        shadowElevation = 4.dp,
        color = MaterialTheme
            .colorScheme.onSurface.copy(alpha = 0.5f)) {

        Image(painter = painterResource(id = R.drawable.profile_image),
            contentDescription = "profile image",
            modifier = modifier.size(135.dp),
            contentScale = ContentScale.Crop)

    }
}

@Composable
private fun CreateInfo() {
    Column(modifier = Modifier.padding(5.dp)) {
        Text(
            text = "Miles P.",
            style = MaterialTheme.typography.titleLarge,
            color = Color(0xFF012AFC)
        )
        Text(
            text = "Android Compose Programmer",
            modifier = Modifier.padding(3.dp),
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = "@myfirstcomposable.com",
            modifier = Modifier.padding(3.dp),
            style = MaterialTheme.typography.titleSmall
        )

    }
}

// Create a surface that holds the profile UI component
@Composable
private fun ProfileImage(modifier: Modifier = Modifier) {
    Surface(
        modifier = Modifier
            .size(150.dp)
            .padding(5.dp),
        shape = CircleShape,
        border = BorderStroke(0.5.dp, Color.LightGray),
        shadowElevation = 4.dp,
        color = MaterialTheme
            .colorScheme.onSurface.copy(alpha = 0.5f)
    )
    {
        // Add image to inside of surface
        Image(
            painter = painterResource(id = R.drawable.profile_image),
            contentDescription = "Profile Image",
            modifier = Modifier.size(135.dp),
            contentScale = ContentScale.Crop
        )
    }
}


//@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BizCardAppTheme {
        CreateBizCard()
    }
}