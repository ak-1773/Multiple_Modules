package com.example.lib_base.config

import java.util.regex.Pattern

/**
 * @author create by Linqy
 * @time 2025-06-26 : 15:49
 * function:
 */
object ArchiveNumberConfig {
    /**
     * 前缀长度
     */
    const val PREFIX_LENGTH: Int = 2

    /**
     * 数字长度
     */
    const val NUM_LENGTH: Int = 5

    /**
     * 总长度
     */
    const val COUNT_LENGTH: Int = PREFIX_LENGTH + NUM_LENGTH

    fun checkArchiveNumber(
        startArchiveNumber: String,
        endArchiveNumber: String,
    ): Pair<Boolean, String> {
        if (startArchiveNumber.isEmpty()) {
            return false to "起始归档号不能为空"
        }
        if (!isArchiveNumber(startArchiveNumber)) {
            return false to "起始归档号格式错误"
        }
        if (endArchiveNumber.isEmpty()) {
            return false to "结束归档号不能为空"
        }
        if (!isArchiveNumber(endArchiveNumber)) {
            return false to "结束归档号格式错误"
        }
        if (startArchiveNumber.toInt() > endArchiveNumber.toInt()) {
            return false to "起始归档号不能大于结束归档号"
        }
        return true to ""
    }

    fun isArchiveNumber(s: String): Boolean {
        val regex = String.format("^[0-9]{%s}+$", NUM_LENGTH)
        return Pattern.matches(regex, s)
    }
}