package com.example.setting.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lib_base.ui.EmptyBorderTextField
import com.example.lib_base.ui.containerColor
import com.example.lib_base.ui.theme.TitleBar
import com.example.setting.model.ServerEvent

@Composable
fun SettingServiceUI(
    uiData: Map<ServerEvent, String>,
    onBack: () -> Unit,
    onConnectClick: (String, String) -> Unit,
    onValueChange: (ServerEvent, String) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = com.example.lib_base.ui.theme.BgPage)
    ) {
        TitleBar(
            onBack = onBack,
            title = "服务器设置",
            rightText = ""
        )
        Column(
            modifier = Modifier
                .padding(horizontal = 0.dp, vertical = 0.dp)
                .background(color = Color.White)
        ) {
            HorizontalDivider()
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 10.dp),
                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "服务器地址",
                    fontSize = 16.sp,
                )
                EmptyBorderTextField(
                    value = uiData[ServerEvent.SERVER_IP] ?: "192.168.1.1",
                    onValueChange = { onValueChange(ServerEvent.SERVER_IP, it) },
                    modifier = Modifier
                        .weight(6f),
                    textStyle = LocalTextStyle.current.copy(
                        fontSize = 16.sp,
                        textAlign = TextAlign.End,
                    ),
                    singleLine = true,
                    colors = containerColor(),
                )
            }
            HorizontalDivider()
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 10.dp),
                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "服务器端口",
                    fontSize = 16.sp,
                )
                EmptyBorderTextField(
                    value = uiData[ServerEvent.SERVER_PORT] ?: "8080",
                    onValueChange = { onValueChange(ServerEvent.SERVER_PORT, it) },
                    modifier = Modifier
                        .weight(6f),
                    textStyle = LocalTextStyle.current.copy(
                        fontSize = 16.sp,
                        textAlign = TextAlign.End,
                    ),
                    singleLine = true,
                    colors = containerColor(),
                )
            }
            HorizontalDivider()
        }
        Button(
            onClick = {
                onConnectClick(
                    uiData[ServerEvent.SERVER_IP] ?: "192.168.1.1",
                    uiData[ServerEvent.SERVER_PORT] ?: "8080"
                )
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = com.example.lib_base.ui.theme.BtnGreen, // 填充颜色
                contentColor = Color.White   // 文字颜色（建议与背景对比）
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 10.dp),
            shape = RoundedCornerShape(2.dp),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Text(
                text = "测试连接",
                fontSize = 15.sp
            )
        }
    }
}

@Preview(device = "spec:width=1920px,height=1080px,dpi=440,orientation=portrait")
@Composable
fun SettingServiceUIPreview() {
    SettingServiceUI(
        emptyMap(),
        onBack = {},
        onConnectClick = { _, _ -> },
        onValueChange = { _, _ -> })
}