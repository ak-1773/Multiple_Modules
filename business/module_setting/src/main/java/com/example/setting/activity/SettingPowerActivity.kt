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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.lib_base.BaseActivity
import com.example.lib_base.ui.theme.ArchivesTheme
import com.example.setting.ui.SettingPowerUI
import com.example.setting.vm.SettingPowerViewModel
import com.therouter.router.Route

@Route(path = "/setting/SettingPowerActivity")
class SettingPowerActivity : BaseActivity() {
    private val viewModel: SettingPowerViewModel by viewModels()

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
                        val uiData by viewModel.uiState.collectAsState()
                        SettingPowerUI(uiData, onBack = { onBack() }, onItemClick = { powerEvent ->
                            viewModel.showDialog(powerEvent)
                        })
                    }
                }
            }
        }
    }

}