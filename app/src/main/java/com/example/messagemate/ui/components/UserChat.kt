package com.example.messagemate.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout


// Created by Shahid Iqbal on 3/9/2023.

@Composable
fun UserChat() {

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        val (profileImage, name, lastMsg, time) = createRefs()

        ProfileImage(path = "",
            onClick = {},
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .constrainAs(profileImage) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                })

        Text(text = "Name", fontWeight = FontWeight.Bold, modifier = Modifier
            .constrainAs(name) {
                top.linkTo(profileImage.top)
                bottom.linkTo(profileImage.top)
                start.linkTo(profileImage.end)
            }
            .padding(start = 10.dp)
            .fillMaxWidth(0.7f)
        )

        Text(text = "Tap to chat", modifier = Modifier
            .constrainAs(lastMsg) {
                top.linkTo(name.bottom)
                start.linkTo(name.start)
                end.linkTo(name.end)
            }
            .fillMaxWidth(0.7f)
            .padding(start = 5.dp, top = 4.dp),color = Color.Gray)

        Text(text = "10:00 am", color = Color.Gray, modifier = Modifier
            .constrainAs(time) {
                top.linkTo(parent.top)
                end.linkTo(parent.end)
            }
            .padding(3.dp))
    }

}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun PreviewChat() {
    UserChat()
}