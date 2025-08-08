package com.example.kv

import android.os.Parcelable
import com.example.kv.config.KvConfig
import com.tencent.mmkv.MMKV
import java.util.Collections

/**
 * @author create by Linqy
 * @time 2025-06-13 : 16:42
 * function: K - V 键值保存管理类
 */
class KVManager(
    val kv: MMKV?,
) {
    //单例
    private object Holder {
        val INSTANCE = KVManager(MMKV.mmkvWithID(KvConfig.ID))
    }

    //派生
    companion object {
        val instance = Holder.INSTANCE
    }

    /**
     * 存储
     */
    fun put(key: String, value: Any?): Boolean {
        return when (value) {
            is String -> kv?.encode(key, value)!!
            is Float -> kv?.encode(key, value)!!
            is Boolean -> kv?.encode(key, value)!!
            is Int -> kv?.encode(key, value)!!
            is Long -> kv?.encode(key, value)!!
            is Double -> kv?.encode(key, value)!!
            is ByteArray -> kv?.encode(key, value)!!
            else -> false
        }
    }

    /**
     * 这里使用安卓自带的Parcelable序列化，它比java支持的Serializer序列化性能好些
     */
    fun <T : Parcelable> put(key: String, t: T?): Boolean {
        if (t == null) {
            return false
        }
        return kv?.encode(key, t)!!
    }

    fun put(key: String, sets: Set<String>?): Boolean {
        if (sets == null) {
            return false
        }
        return kv?.encode(key, sets)!!
    }

    fun getInt(key: String): Int {
        kv?.apply { return decodeInt(key) }
        return 0
    }

    fun getInt(key: String, def: Int = 0): Int {
        kv?.apply { return decodeInt(key, def) }
        return def
    }

    fun getDouble(key: String, def: Double = 0.0): Double {
        kv?.apply {
            return decodeDouble(key, def)
        }
        return def
    }

    fun getLong(key: String, def: Long = 0L): Long {
        kv?.apply { return decodeLong(key, def) }
        return def
    }

    fun getBoolean(key: String, def: Boolean = false): Boolean {
        kv?.apply { return decodeBool(key, def) }
        return def
    }

    fun getFloat(key: String, def: Float = 0f): Float {
        kv?.apply { return decodeFloat(key, def) }
        return def
    }

    fun getByteArray(key: String): ByteArray? {
        return kv?.decodeBytes(key)
    }

    fun getString(key: String): String? {
        return kv?.decodeString(key, "")
    }

    fun getString(key: String, def: String): String {
        val value = kv?.decodeString(key, "")
        if (value?.isBlank() == true) {
            return def
        }
        return value ?: ""
    }

    /**
     * SpUtils.getParcelable<Class>("")
     */
    inline fun <reified T : Parcelable> getParcelable(key: String): T? {
        return kv?.decodeParcelable(key, T::class.java)
    }

    fun getStringSet(key: String): Set<String>? {
        return kv?.decodeStringSet(key, Collections.emptySet())
    }

    fun removeKey(key: String) {
        kv?.removeValueForKey(key)
    }

    fun clearAll() {
        kv?.clearAll()
    }
}