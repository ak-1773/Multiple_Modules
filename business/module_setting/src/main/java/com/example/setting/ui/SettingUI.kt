package com.example.setting.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lib_base.ui.theme.TitleBar
import com.example.setting.model.ClickEvent

@Composable
fun SettingUI(onBack: () -> Unit, onItemClick: (ClickEvent) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = com.example.lib_base.ui.theme.BgPage)
    ) {
        TitleBar(
            onBack = onBack,
            title = "设置",
            rightText = ""
        )
        Column(
            modifier = Modifier
                .padding(horizontal = 0.dp, vertical = 0.dp)
                .weight(1f)
                .background(color = Color.White)
        ) {
            HorizontalDivider()
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onItemClick(ClickEvent.MODULE) }
                    .padding(horizontal = 10.dp, vertical = 10.dp),
                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "更新设置",
                    fontSize = 16.sp,
                )
                Icon(
                    painter = painterResource(id = com.example.lib_base.R.drawable.ic_arrow_right),
                    contentDescription = null,
                    modifier = Modifier
                        .size(16.dp),
                    tint = Color.Gray
                )
            }

            HorizontalDivider()
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onItemClick(ClickEvent.SERVER) }
                    .padding(horizontal = 10.dp, vertical = 10.dp),
                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "服务器设置",
                    fontSize = 16.sp,
                )
                Icon(
                    painter = painterResource(id = com.example.lib_base.R.drawable.ic_arrow_right),
                    contentDescription = null,
                    modifier = Modifier
                        .size(16.dp),
                    tint = Color.Gray
                )

            }
            HorizontalDivider()
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onItemClick(ClickEvent.ABOUT) }
                    .padding(horizontal = 10.dp, vertical = 10.dp),
                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "关于系统",
                    fontSize = 16.sp,
                )
                Icon(
                    painter = painterResource(id = com.example.lib_base.R.drawable.ic_arrow_right),
                    contentDescription = null,
                    modifier = Modifier
                        .size(16.dp),
                    tint = Color.Gray
                )

            }
            HorizontalDivider()
        }
    }
}

@Preview(device = "spec:width=1920px,height=1080px,dpi=440,orientation=portrait")
@Composable
fun SettingUIPreview() {
    SettingUI(onBack = {}, onItemClick = {})
}