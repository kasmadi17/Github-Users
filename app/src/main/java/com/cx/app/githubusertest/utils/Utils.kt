package com.cx.app.githubusertest.utils

import android.content.res.Resources
import com.google.gson.Gson

/**
 * @author kasmadi
 * @date 1/15/21
 */

inline fun <reified T> getError(key: String?): T? {
    val gson = Gson()
    return gson.fromJson(key, T::class.java)
}

fun isNotNull(text: String?): Boolean {
    var tempText: String? = text ?: return false
    tempText = tempText!!.trim { it <= ' ' }
    return tempText != ""
}

fun isNotNull(list: List<*>?): Boolean {
    return list != null && list.isNotEmpty()
}

val Int.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()
