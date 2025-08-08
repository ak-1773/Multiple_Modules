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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kv.KVManager
import com.example.kv.key.KeySet
import com.example.lib_base.config.DefaultValueConfig
import com.example.lib_base.ui.theme.TitleBar
import com.example.setting.model.PowerEvent

@Composable
fun SettingPowerUI(
    uiData: Map<PowerEvent, Int>,
    onBack: () -> Unit,
    onItemClick: (PowerEvent) -> Unit,

    ) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = com.example.lib_base.ui.theme.BgPage)
    ) {
        TitleBar(
            onBack = onBack,
            title = "RFID读取设置",
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
                    .clickable { onItemClick(PowerEvent.POWER_CHECK) }
                    .padding(horizontal = 10.dp, vertical = 10.dp),
                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "档案核对",
                    fontSize = 16.sp,
                )
                Row(
                    verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    val checkPowerValue =
                        remember { mutableIntStateOf(DefaultValueConfig.DEFAULT_POWER) }
                    val powerCheck = KVManager.instance.getInt(KeySet.KEY_POWER_CHECK)
                    if (powerCheck != 0) {
                        checkPowerValue.intValue = powerCheck
                    }
                    Text(
                        text = "${uiData[PowerEvent.POWER_CHECK]}\u0020dBm",
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
            }
            HorizontalDivider()
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onItemClick(PowerEvent.POWER_FIND) }
                    .padding(horizontal = 10.dp, vertical = 10.dp),
                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                val findPowerValue =
                    remember { mutableIntStateOf(DefaultValueConfig.DEFAULT_POWER) }
                val powerFind = KVManager.instance.getInt(KeySet.KEY_POWER_FIND)
                if (powerFind != 0) {
                    findPowerValue.intValue = powerFind
                }
                Text(
                    text = "档案查找",
                    fontSize = 16.sp,
                )
                Row(
                    verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "${uiData[PowerEvent.POWER_FIND]}\u0020dBm",
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
            }
            HorizontalDivider()
        }
    }
}

@Preview(device = "spec:width=1920px,height=1080px,dpi=440,orientation=portrait")
@Composable
fun SettingPowerUIPreview() {
    SettingPowerUI(emptyMap(), onBack = {}, onItemClick = {})
}