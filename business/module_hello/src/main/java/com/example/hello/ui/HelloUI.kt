package com.example.hello.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun HelloUI() {
    Text(text = "Hello World!")
}

@Preview(device = "spec:width=1920px,height=1080px,dpi=440,orientation=portrait")
@Composable
fun HelloPreview() {
    HelloUI()
}