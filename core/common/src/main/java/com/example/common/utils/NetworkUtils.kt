package com.example.common.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket


/**
 * @author create by Linqy
 * @time 2024-09-11 : 17:33
 * function:网络检查
 */
class NetworkUtils {
    companion object {
        /**
         * 网络是否畅通
         */
        fun isNetworkAvailable(context: Context, host: String, port: String): Pair<Boolean, String> {
            // 1. 检查网络连接状态
            if (!isNetworkConnected(context)) {
                return false to "网络未连接"
            }
            // 2. 测试目标服务器是否可达
            if (!isServerReachable(host, port)) {
                return false to "无法连接服务器"
            }
            return true to "网络连接正常"
        }

        /**
         * 检查网络是否连接
         */
        fun isNetworkConnected(context: Context): Boolean {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                val activeNetwork = connectivityManager.activeNetwork ?: return false
                val capabilities =
                    connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false

                return when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    // 包括USB共享网络
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                    else -> false
                }
            } else {
                // Android 5.1及以下版本
                val activeNetworkInfo = connectivityManager.activeNetworkInfo
                return activeNetworkInfo != null && activeNetworkInfo.isConnected
            }
        }

        /**
         * 目标服务器是否可达
         */
        fun isServerReachable(host: String, port: String): Boolean {
            return try {
                Socket().use { socket ->
                    socket.connect(InetSocketAddress(host, port.toInt()), 3000)
                    true
                }
                //val pingResult = InetAddress.getByName(host).isReachable(3000) // 3秒超时
                //pingResult
            } catch (e: IOException) {
                false
            }
        }
    }

}