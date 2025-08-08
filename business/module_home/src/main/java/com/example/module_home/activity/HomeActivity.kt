package com.example.module_home.activity

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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.lib_base.BaseActivity
import com.example.lib_base.expand.CenteredDialog
import com.example.lib_base.expand.ErrorDialog
import com.example.lib_base.expand.LoadingDialog
import com.example.lib_base.expand.LoadingPercentDialog
import com.example.lib_base.ui.theme.ArchivesTheme
import com.example.module_home.ui.theme.HomeMenuList
import com.example.module_home.vm.HomeViewModel
import com.therouter.router.Route
import dagger.hilt.android.AndroidEntryPoint

@Route(path = "/home/HomeActivity")
@AndroidEntryPoint
class HomeActivity : BaseActivity() {
    private val viewModel: HomeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArchivesTheme {
                val uiState by viewModel.uiState.collectAsState()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Surface(
                        modifier = Modifier
                            .padding(innerPadding)
                            .background(Color.White)
                    ) {
                        // 错误处理
                        if (uiState.error != null) {
                            ErrorDialog(
                                uiState.error!!,
                                onConfirmClick = { finish() })
                        }
                        // 正常显示
                        if (uiState.menuItems.isNotEmpty()) {
                            HomeMenuList(uiState.menuItems, onItemClick = { clickEven ->
                                viewModel.onClick(clickEven)
                            })
                        }
                        ShowDialog()
                    }
                }
            }
        }
        viewModel.loadMenuData(context = this)
    }

    @Composable
    private fun ShowDialog() {
        val showDialog by viewModel.showDialog.collectAsState()
        if (showDialog) {
            CenteredDialog(
                onDismiss = { viewModel.hideDialog() },
                content = {
                    LoadingDialog()
                })
        }

    }
}
