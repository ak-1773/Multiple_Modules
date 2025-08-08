package com.example.lib_base

import android.app.Application
import android.content.Context
import com.elvishew.xlog.LogConfiguration
import com.elvishew.xlog.LogLevel
import com.elvishew.xlog.XLog
import com.elvishew.xlog.interceptor.BlacklistTagsFilterInterceptor
import com.elvishew.xlog.printer.AndroidPrinter
import com.elvishew.xlog.printer.Printer
import com.elvishew.xlog.printer.file.FilePrinter
import com.elvishew.xlog.printer.file.backup.NeverBackupStrategy
import com.elvishew.xlog.printer.file.clean.FileLastModifiedCleanStrategy
import com.elvishew.xlog.printer.file.naming.DateFileNameGenerator
import com.elvishew.xlog.printer.file.writer.SimpleWriter
import com.example.lib_base.worker.WorkHelper
import com.tencent.mmkv.MMKV
import com.therouter.TheRouter
import java.io.File
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * @author create by Linqy
 * @time 2025-06-10 : 14:24
 * function:
 */
open class BaseApplication : Application() {
    companion object {
        private var appContext: Context by NotNullSingleValue()
        fun instance() = appContext
    }

    class NotNullSingleValue<T>() : ReadWriteProperty<Any?, T> {
        private var value: T? = null
        override fun getValue(thisRef: Any?, property: KProperty<*>): T {
            return value ?: throw IllegalStateException("Context not initialized")
        }

        override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
            this.value = if (this.value == null) {
                value
            } else {
                throw throw IllegalStateException("Context already initialized")
            }
        }
    }

    override fun attachBaseContext(base: Context?) {
        TheRouter.isDebug = BuildConfig.DEBUG
        super.attachBaseContext(base)
    }

    override fun onCreate() {
        super.onCreate()
        appContext = this
        init()
    }

    private fun init() {
        // 1、可以修改目录的初始化 目录：/data/data/包名/files/mmkv
        // val path = filesDir.absolutePath + "/mmkv"
        // MMKV.initialize(path)

        //2、普通初始化 目录：/data/data/包名/files
        MMKV.initialize(this)
        // 1和2使用一个就可以了

        xLogInit()

        //初始化work
        try {
            WorkHelper.scheduleLoopWork(this)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    private fun xLogInit() {
        val config = LogConfiguration.Builder()
            // 指定日志级别，低于该级别的日志将不会被打印，默认为 LogLevel.ALL
            .logLevel(
                LogLevel.ALL
            )
            // 指定 TAG，默认为 "X-LOG"
            .tag("Archives")
            .enableBorder()
            // 允许打印线程信息，默认禁止
            //.enableThreadInfo()
            // 允许打印深度为 3 的调用栈信息，默认禁止
            //.enableStackTrace(3)
            //添加 TAG 过滤器
            .addInterceptor(BlacklistTagsFilterInterceptor("L", "X"))
            .build()


        // 通过 android.util.Log 打印日志的打印器
        val androidPrinter: Printer =
            AndroidPrinter(true)
        // 打印日志到文件的打印器

        val filePrinter: Printer = FilePrinter
            // 指定保存日志文件的路径
            .Builder(this.externalCacheDir?.absolutePath + File.separator + "log")
            // 指定日志文件名生成器，默认为 ChangelessFileNameGenerator("log")
            .fileNameGenerator(DateFileNameGenerator())
            // 指定日志文件备份策略，默认为 FileSizeBackupStrategy(1024 * 1024)
            .backupStrategy(NeverBackupStrategy())
            // 指定日志文件清除策略，默认为 NeverCleanStrategy() 24小时清理
            .cleanStrategy(FileLastModifiedCleanStrategy(24 * 60 * 60 * 1000))
            // 指定日志写入器，默认为 SimpleWriter
            .writer(object : SimpleWriter() {
                override fun open(file: File): Boolean {
                    //指定文件名
                    return super.open(File(file.path + ".txt"))
                }
            })
            .build();

        // 初始化 XLog
        XLog.init(
            // 指定日志配置，如果不指定，会默认使用 new LogConfiguration.Builder().build()
            config,
            // 添加任意多的打印器。如果没有添加任何打印器，会默认使用 AndroidPrinter(Android)/ConsolePrinter(java)
            androidPrinter,
            filePrinter
        );
    }
}