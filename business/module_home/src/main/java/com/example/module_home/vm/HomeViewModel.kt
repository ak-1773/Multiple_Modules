package com.example.module_home.vm

import android.content.Context
import androidx.lifecycle.viewModelScope
import com.example.home_module.R
import com.example.lib_base.BaseViewModel
import com.example.lib_base.config.DurationConfig
import com.example.lib_base.toast.XToast
import com.example.lib_db.repository.DbRepository
import com.example.module_home.model.ClickEvent
import com.example.module_home.model.MenuItem
import com.therouter.TheRouter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

/**
 * @author create by Linqy
 * @time 2025-06-10 : 11:10
 * function:主页面vm
 */
@OptIn(kotlinx.coroutines.FlowPreview::class)
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val dbRepository: DbRepository,
    val xt: XToast,
) : BaseViewModel() {
    // 定义状态类
    data class State(
        val menuItems: List<MenuItem> = emptyList(),
        val error: String? = null,
    )

    // 状态流暴露给View层
    private val _uiState = MutableStateFlow(State())
    val uiState: StateFlow<State> = _uiState.asStateFlow()

    //弹窗事件流
    private val _showDialog = MutableStateFlow(false)
    val showDialog: StateFlow<Boolean> = _showDialog.asStateFlow()

    private val _clickEvents = MutableSharedFlow<ClickEvent>(replay = 1)

    init {
        // 监听点击事件
        _clickEvents
            // 300ms 的防抖时间
            .debounce(DurationConfig.DURATION_ANTI_SHAKE)
            .onEach { clickEvent ->
                when (clickEvent) {
                    ClickEvent.HELLO -> {
                        //点击事件
                        TheRouter.build("/hello/HelloActivity")
                            .navigation()

                    }

                    ClickEvent.TEST -> {
                        //点击事件
                        TheRouter.build("/test/TestActivity")
                            .navigation()
                    }

                    ClickEvent.SETTING -> {
                        //设置
                        TheRouter.build("/setting/SettingActivity").navigation()
                    }
                }
            }.launchIn(viewModelScope)
    }


    fun loadMenuData(context: Context) {
        try {
            val iconArray =
                context.resources.obtainTypedArray(R.array.menu_icon)
            val titleArray =
                context.resources.obtainTypedArray(R.array.menu_title)
            if (iconArray.length() != titleArray.length()) {
                throw IllegalArgumentException("图标和标题数量不一致")
            }

            val items: List<MenuItem> = (0 until iconArray.length()).map { index ->
                MenuItem(iconArray.getResourceId(index, 0), titleArray.getString(index)!!)
            }

            _uiState.update { it.copy(menuItems = items) }
            iconArray.recycle()
            titleArray.recycle()
        } catch (e: Exception) {
            _uiState.update { it.copy(error = e.message) }
        }
    }

    fun onClick(clickEvent: ClickEvent) {
        _clickEvents.tryEmit(clickEvent)
    }

    fun hideDialog() {
        _showDialog.value = false
    }
}