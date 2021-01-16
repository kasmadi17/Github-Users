package com.cx.app.githubusertest.ui.base

/**
 * @author kasmadi
 * @date 1/15/21
 */
interface BasePresenter<T:BaseView> {
    fun attachView(view:T)
    fun detachView()
}