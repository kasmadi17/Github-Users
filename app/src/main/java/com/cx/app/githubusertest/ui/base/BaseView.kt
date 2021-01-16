package com.cx.app.githubusertest.ui.base

import android.view.View

/**
 * @author kasmadi
 * @date 1/15/21
 */
interface BaseView {
    fun onAttachView()
    fun onDetachView()
    fun onDataEmpty(msg: String)
    fun onError(msg: String?)
    fun onLoading(isLoading: Boolean = true)
}