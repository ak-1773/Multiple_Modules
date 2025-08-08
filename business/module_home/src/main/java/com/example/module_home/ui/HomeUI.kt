package com.example.module_home.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.home_module.R
import com.example.lib_base.ui.theme.MenuGray
import com.example.module_home.model.ClickEvent
import com.example.module_home.model.MenuItem

/**
 * @author create by Linqy
 * @time 2025-06-10 : 11:22
 * function:
 */
@Composable
fun HomeMenuList(items: List<MenuItem>, onItemClick: (ClickEvent) -> Unit) {

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(top = 36.dp, start = 16.dp, end = 16.dp)
    ) {
        items(items.size) { index ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .height(84.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(color = MenuGray)
                    .clickable {
                        onItemClick(
                            when (index) {
                                0 -> ClickEvent.HELLO
                                1 -> ClickEvent.TEST
                                else -> {
                                    ClickEvent.SETTING
                                }
                            }
                        )
                    }
            ) {
                Row(
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 12.dp, vertical = 8.dp)
                ) {
                    Image(
                        painter = androidx.compose.ui.res.painterResource(id = items[index].icon),
                        contentDescription = items[index].title,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color.Transparent, shape = RoundedCornerShape(10.dp))
                ) {
                    Text(
                        textAlign = TextAlign.Center,
                        text = items[index].title,
                        fontSize = 24.sp
                    )
                }

            }
        }
    }
}

@Preview(device = "spec:width=1920px,height=1080px,dpi=440,orientation=portrait")
@Composable
fun Preview() {
    //TitleBar()
    HomeMenuList(
        items = listOf(
            MenuItem(R.drawable.menu_hello, "Hello"),
            MenuItem(R.drawable.menu_test, "Test"),
            MenuItem(R.drawable.menu_setting, "Setting"),
        ), onItemClick = {
            // Handle item click here
        }
    )
}