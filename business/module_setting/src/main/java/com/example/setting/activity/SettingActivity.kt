package com.example.setting.activity

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.lib_base.BaseActivity
import com.example.lib_base.ui.theme.ArchivesTheme
import com.example.setting.ui.SettingUI
import com.example.setting.vm.SettingViewModel
import com.therouter.router.Route

@Route(path = "/setting/SettingActivity")
class SettingActivity : BaseActivity() {
    private val viewModel: SettingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArchivesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Surface(
                        modifier = Modifier
                            .padding(innerPadding)
                            .background(Color.White)
                    ) {

                        SettingUI(onBack = { onBack() }, onItemClick = {
                            viewModel.onClick(it)
                        })
                    }
                }
            }
        }
    }

}


