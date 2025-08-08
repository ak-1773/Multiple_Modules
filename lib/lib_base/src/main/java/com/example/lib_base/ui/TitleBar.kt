package com.example.lib_base.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lib_base.R

/**
 * @author create by Linqy
 * @time 2025-06-12 : 11:41
 * function:
 */
@Composable
fun TitleBar(
    onBack: () -> Unit = {},
    title: String = "",
    rightText: String = "",
    onRightClick: () -> Unit = {},
) {
    Row(
        modifier = Modifier
            .height(48.dp)
            .fillMaxWidth()
            .background(color = Color.White)
            .padding(5.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_arrow_back),
            contentDescription = "back",
            modifier = Modifier
                .size(32.dp)
                .clickable {
                    onBack()
                }
                .padding(5.dp)
                .weight(1.2f),
        )
        Text(
            text = title,
            modifier = Modifier
                .weight(7f)
                .height(32.dp)
                .wrapContentHeight(Alignment.CenterVertically),
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
        Box(modifier = Modifier.weight(1.2f), contentAlignment = Alignment.Center) {
            if (rightText.isNotEmpty()) {
                Text(
                    text = rightText,
                    modifier = Modifier
                        .height(32.dp)
                        .fillMaxWidth()
                        .clickable {
                            onRightClick()
                        }
                        .padding(4.dp)
                        .wrapContentHeight(Alignment.CenterVertically),
                    fontSize = 15.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview(device = "spec:width=1920px,height=1080px,dpi=440,orientation=portrait")
@Composable
fun Preview() {
    TitleBar()
}