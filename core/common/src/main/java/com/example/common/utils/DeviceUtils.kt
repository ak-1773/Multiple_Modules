package com.example.common.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import com.example.common.utils.StringUtils.Companion.isEmpty
import java.io.FileInputStream
import java.io.IOException
import java.nio.charset.Charset
import java.util.Locale

/**
 * @author create by Linqy
 * @time 2025-06-16 : 10:09
 * function:
 */
class DeviceUtils {
    companion object {
        @SuppressLint("MissingPermission")
        fun getSN(): String {
            var sn = ""
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                try {
                    sn = Build.getSerial()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            } else {
                sn = Build.SERIAL
            }
            if (isEmpty(sn) || Build.UNKNOWN == sn) {
                try {
                    val c = Class.forName("android.os.SystemProperties")
                    val get = c.getMethod("get", String::class.java)
                    sn = get.invoke(c, "ro.serialno") as String
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            val serialLength = 7
            if (sn.length != serialLength) {
                sn = getEth0Mac()
                //去除 ":", 改大写
                sn = sn.replace(":".toRegex(), "").uppercase(Locale.getDefault())
            }
            return sn
        }

        /**
         * eth0 MAC地址获取，适用api9 - api24
         */
        fun getEth0Mac(): String {
            var mac = ""
            var path = "sys/class/net/eth0/address"
            var fis_name: FileInputStream? = null
            var fis: FileInputStream? = null
            try {
                fis_name = FileInputStream(path)
                val buffer_name = ByteArray(1024 * 8)
                val byteCount_name = fis_name.read(buffer_name)
                if (byteCount_name > 0) {
                    mac = String(buffer_name, 0, byteCount_name, Charset.defaultCharset())
                }
                if (mac.length == 0) {
                    path = "sys/class/net/eth0/wlan0"
                    fis = FileInputStream(path)
                    val buffer = ByteArray(1024 * 8)
                    val byteCount = fis.read(buffer)
                    if (byteCount > 0) {
                        mac = String(buffer, 0, byteCount, Charset.defaultCharset())
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                if (null != fis_name) {
                    try {
                        fis_name.close()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
                if (null != fis) {
                    try {
                        fis.close()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }
            return mac.trim { it <= ' ' }
        }

        /**
         * 获取当前app version code
         */
        fun getVersionCode(context: Context): Long {
            var appVersionCode: Long = 0
            try {
                val packageInfo = context.applicationContext
                    .packageManager
                    .getPackageInfo(context.packageName, 0)
                appVersionCode = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    packageInfo.longVersionCode
                } else {
                    packageInfo.versionCode.toLong()
                }
            } catch (e: PackageManager.NameNotFoundException) {
            }
            return appVersionCode
        }

        /**
         * 获取当前app version name
         */
        fun getAppVersionName(context: Context): String {
            var appVersionName = ""
            try {
                val packageInfo = context.applicationContext
                    .packageManager
                    .getPackageInfo(context.packageName, 0)
                appVersionName = packageInfo.versionName ?: ""
            } catch (e: PackageManager.NameNotFoundException) {
                Log.e("", e.message!!)
            }
            return appVersionName
        }
    }
}