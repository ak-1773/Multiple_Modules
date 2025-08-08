package com.example.lib_base.expand

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

/**
 * @author create by Linqy
 * @time 2025-06-09 : 10:33
 * function: 拓展
 */
@Composable
fun ErrorDialog(
    errorMsg: String?,
    onConfirmClick: () -> Unit,
) = AlertDialog(
    onDismissRequest = {},
    confirmButton = {
        TextButton(
            onClick = {
                onConfirmClick()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("确定", fontSize = 18.sp)
        }
    },
    title = { Text("错误") },
    text = { Text(errorMsg ?: "") },
    icon = null,
    modifier = Modifier,
    shape = RoundedCornerShape(5.dp)

)

@Composable
fun CenteredDialog(
    onDismiss: () -> Unit = {},
    content: @Composable () -> Unit,
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false),
    ) {
        Surface(
            // 控制宽度为屏幕80%
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .background(
                    color = Color.Transparent,
                ),
            shape = RoundedCornerShape(5.dp)

        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = Color.White,
                    )
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                content()
            }
        }

    }
}

@Composable
fun LoadingDialog(
    msg: String = "加载中...",
) {
    Column(
        modifier = Modifier
            .padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(msg, modifier = Modifier.padding(8.dp))
        CircularProgressIndicator(
            modifier = Modifier
                .size(32.dp)
                .padding(3.dp),
            color = com.example.lib_base.ui.theme.BtnGreen
        )
    }
}

@Composable
fun LoadingPercentDialog(
    msg: String = "加载中...",
    progress: Int = 0,
    total: Int = 100,
) {
    Column(
        modifier = Modifier
            .padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(msg, modifier = Modifier.padding(8.dp))
        LinearProgressIndicator(
            progress = {
                var calculatedProgress: Float = progress.toFloat() / total
                if (calculatedProgress > 1f) {
                    calculatedProgress = 1f
                }
                calculatedProgress // 进度值（0~1）
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 6.dp)
                .height(18.dp)// 自定义高度
                .clip(RoundedCornerShape(32.dp)),
            color = com.example.lib_base.ui.theme.BtnGreen, // 自定义颜色
            trackColor = com.example.lib_base.ui.theme.MenuGray, // 背景轨道颜色
        )
        Text(
            "${progress}/${total}",
            modifier = Modifier.padding(8.dp),
            fontSize = 12.sp
        )
    }
}

@Preview(device = "spec:width=1920px,height=1080px,dpi=440,orientation=portrait")
@Composable
fun LoadingDialogPreview() {
    CenteredDialog({}, content = { LoadingPercentDialog() })
}