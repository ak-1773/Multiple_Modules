package com.example.common.utils

import java.util.regex.Pattern

/**
 * @author create by Linqy
 * @time 2024-09-11 : 10:47
 * function:
 */
class StringUtils {
    companion object {
        /**
         * 十六进制字符
         */
        private val S_HEXADECIMAL = "0123456789ABCDEF"

        /**
         * 十六进制字符串数组转字符
         */
        private var sBytes: ByteArray? = null

        /**
         * 判断空字符串
         *
         * @param cs 字符
         * @return true 空
         */
        @JvmStatic
        fun isEmpty(cs: CharSequence?): Boolean {
            return cs == null || cs.isEmpty()
        }

        /**
         * 判断空字符串
         *
         * @param cs 字符
         * @return true 不空
         */
        @JvmStatic
        fun isNotEmpty(cs: CharSequence?): Boolean {
            return !isEmpty(cs)
        }

        /**
         * 安全空串
         *
         * @param cs 字符
         * @return cs 为 null 返回 "", 否则原样返回
         */
        @JvmStatic
        fun secureEmpty(cs: CharSequence): String {
            return if (isEmpty(cs)) {
                ""
            } else cs.toString()
        }

        /**
         * 判断是否是字母
         *
         * @param s 待判断字符串
         * @return true:是, false: 否
         */
        @JvmStatic
        fun isAlphabetic(s: String?): Boolean {
            val regex = "^[a-zA-Z]+$"
            return if (s == null) {
                return false
            } else {
                Pattern.matches(regex, s)
            }
        }

        /**
         * 判断是否是数字
         *
         * @param s 检查源
         * @return true:是, false: 否
         */
        @JvmStatic
        fun isNumber(s: String?): Boolean {
            val regex = "^[0-9]+$"
            return Pattern.matches(regex, s)
        }

        /**
         * 十六进制转换字符串
         *
         * @param hexStr str Byte字符串(Byte之间无分隔符 如:[616C6B])
         * @return String 对应的字符串
         */
        @JvmStatic
        fun hexStr2Str(hexStr: String): String {
            val hexCharArr = hexStr.toCharArray()
            val length = hexStr.length shr 1
            if (null == sBytes || sBytes!!.size != length) {
                sBytes = ByteArray(length)
            }
            var n: Int
            for (i in sBytes!!.indices) {
                val index = i shl 1
                n = S_HEXADECIMAL.indexOf(hexCharArr[index]) shl 4
                n += S_HEXADECIMAL.indexOf(hexCharArr[index + 1])
                sBytes!![i] = (n and 0xff).toByte()
            }
            return String(sBytes!!)
        }

        /**
         * IP 正则表达式
         *
         * @param ipAddress ip地址
         * @return 正则结果 1. true:是; 2. false:非
         */
        @JvmStatic
        fun isIpAddress(ipAddress: String): Boolean {
            val ipRule =
                "^((2((5[0-5])|([0-4]\\d)))|([0-1]?\\d{1,2}))(\\.((2((5[0-5])|([0-4]\\d)))|([0-1]?\\d{1,2}))){3}$"
            if (!isEmpty(ipAddress)) {
                val minLength = 6
                val maxLength = 16
                val length = ipAddress.length
                if (length in (minLength + 1) until maxLength) {
                    val compile = Pattern.compile(ipRule)
                    val matcher = compile.matcher(ipAddress)
                    return matcher.matches()
                }
            }
            return false
        }

        /**
         * 端口号正则表达式
         */
        @JvmStatic
        fun isPort(port: String): Boolean{
            val portRule = "^([0-9]|[1-9]\\d|[1-9]\\d{2}|[1-9]\\d{3}|[1-5]\\d{4}|6[0-4]\\d{3}|65[0-4]\\d{2}|655[0-2]\\d|6553[0-5])$"
            val compile = Pattern.compile(portRule)
            val matcher = compile.matcher(port)
            return matcher.matches()
        }
    }
}