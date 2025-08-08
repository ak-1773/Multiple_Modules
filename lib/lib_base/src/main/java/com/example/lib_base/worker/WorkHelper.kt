package com.example.lib_base.worker

import android.content.Context
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

/**
 * @author create by Linqy
 * @time 2025-07-17 : 17:56
 * function:
 */
class WorkHelper {
    companion object {
        fun scheduleLoopWork(context: Context) {
            val uniqueWorkName = "loop_work"
            if (WorkManager.getInstance(context).getWorkInfosForUniqueWork(uniqueWorkName).get()
                    .isEmpty()
            ) {
                val constraints: Constraints = Constraints.Builder()
                    .build()

                val periodicWorkRequest: PeriodicWorkRequest =
                    PeriodicWorkRequest.Builder(
                        LoopWorker::class.java,
                        12, TimeUnit.HOURS,
                        15, TimeUnit.MINUTES
                    )
                        .setConstraints(constraints)
                        .build()

                WorkManager.getInstance(context).enqueueUniquePeriodicWork(
                    uniqueWorkName,
                    ExistingPeriodicWorkPolicy.KEEP,
                    periodicWorkRequest
                )
            }
        }
    }
}