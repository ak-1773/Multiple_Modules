package com.example.lib_base.worker

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.kv.KVManager
import com.example.kv.key.KeySet


/**
 * @author create by Linqy
 * @time 2025-07-17 : 17:51
 * function:
 */
open class LoopWorker(val context: Context, params: WorkerParameters) : Worker(context, params) {
    override fun doWork(): Result {
        performLoopTask()
        return Result.success()
    }

    private fun performLoopTask() {
        KVManager.instance.put(KeySet.KEY_DEBUG, false)

    }
}