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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.lib_base.BaseActivity
import com.example.lib_base.expand.CenteredDialog
import com.example.lib_base.expand.LoadingDialog
import com.example.lib_base.ui.theme.ArchivesTheme
import com.example.setting.ui.SettingServiceUI
import com.example.setting.vm.SettingServerViewModel
import com.therouter.router.Route
import dagger.hilt.android.AndroidEntryPoint

@Route(path = "/setting/SettingServiceActivity")
@AndroidEntryPoint
class SettingServiceActivity : BaseActivity() {
    private val viewModel: SettingServerViewModel by viewModels()

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
                        SettingServiceUI(
                            uiData,
                            onBack = { onBack() },
                            onConnectClick = { ip, port ->
                                run {
                                    if (viewModel.checkServerInfo(ip, port)) {
                                        viewModel.saveServerInfo(ip, port)
                                    }
                                }
                            },
                            onValueChange = { event, value ->
                                viewModel.updateServerInfo(event, value)
                            })
                        ShowLoadingDialog()
                    }
                }
            }
        }
        viewModel.loadServerData()
    }

    @Composable
    private fun ShowLoadingDialog() {
        val showDialog by viewModel.showDialog.collectAsState()
        if (showDialog) {
            CenteredDialog(onDismiss = { viewModel.hideDialog() }, content = { LoadingDialog() })
        }
    }
}