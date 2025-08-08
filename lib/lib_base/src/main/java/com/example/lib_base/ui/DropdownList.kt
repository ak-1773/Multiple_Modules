package com.example.lib_base.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

/**
 * @author create by Linqy
 * @time 2025-06-16 : 17:38
 * function:
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownList(
    modifier: Modifier = Modifier,
    options: List<String>,
    tip: String = "请选择:",
    onSelected: (Int) -> Unit = {},
) {
    var expanded by remember { mutableStateOf(false) }
    var dropdownDismissed by remember { mutableStateOf(true) }
    var selectedOption by remember { mutableStateOf(options.first()) }
    val interactionSource = remember { MutableInteractionSource() }
    LaunchedEffect(expanded) {
        if (!expanded) {
            delay(100)
            dropdownDismissed = true
        }
    }
    Row(
        modifier = modifier
            .widthIn(max = 160.dp)
            .heightIn(max = 32.dp)
            .border(1.dp, Color.Gray, RoundedCornerShape(32.dp))
            .padding(horizontal = 8.dp, vertical = 1.dp)
            .clickable(
                interactionSource = interactionSource,
                indication = null, // 禁用点击效果
                onClick = {
                    if (dropdownDismissed) {
                        dropdownDismissed = false
                        expanded = !expanded
                    }
                }
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(0.dp)
    ) {
        Text(
            tip, modifier = Modifier
                .padding(horizontal = 1.dp), fontSize = 13.sp
        )

        Box(
            modifier = Modifier
                .weight(1f)
        ) {
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = {
                    expanded = it
                }
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(start = 0.dp, end = 0.dp)
                ) {
                    EmptyBorderTextField(
                        value = selectedOption,
                        onValueChange = {},
                        readOnly = true,
                        modifier = Modifier
                            .menuAnchor()
                            .weight(1f),
                        textStyle = TextStyle(
                            fontSize = 13.sp, // 设置字体大小为24sp
                            textAlign = TextAlign.End
                        ),
                        colors = containerColor()
                    )
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded)
                }


                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = {
                        expanded = false
                    },
                    modifier = Modifier
                        .widthIn(min = 128.dp, max = 256.dp)
                        .background(
                            color = com.example.lib_base.ui.theme.MenuGray,
                        )
                        .clip(RoundedCornerShape(8.dp))
                ) {
                    options.forEach { option ->
                        DropdownMenuItem(
                            text = {
                                Text(
                                    option,
                                    modifier = Modifier.widthIn(min = 128.dp, max = 256.dp),
                                    fontSize = 16.sp
                                )
                            },
                            onClick = {
                                selectedOption = option
                                expanded = false
                                onSelected(options.lastIndexOf(option))
                            }
                        )
                    }
                }
            }
        }
    }
}

@Preview(device = "spec:width=1920px,height=1080px,dpi=440,orientation=portrait")
@Composable
fun PreviewDropdownList() {
    DropdownList(options = listOf("全部", "已核对", "待核对"))
}