package com.cx.app.githubusertest.ui.users

import com.cx.app.githubusertest.model.Items
import com.cx.app.githubusertest.model.UsersModel
import com.cx.app.githubusertest.ui.base.BaseView

/**
 * @author kasmadi
 * @date 1/15/21
 */

interface SearchUserView :BaseView{
    fun onSuccess(data: UsersModel?)
}
