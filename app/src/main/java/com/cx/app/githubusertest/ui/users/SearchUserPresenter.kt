package com.cx.app.githubusertest.ui.users

import com.cx.app.githubusertest.model.ErrorResponse
import com.cx.app.githubusertest.model.UsersModel
import com.cx.app.githubusertest.networking.ApiClient
import com.cx.app.githubusertest.ui.base.BasePresenter
import com.cx.app.githubusertest.utils.getError
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * @author kasmadi
 * @date 1/15/21
 */
class SearchUserPresenter : BasePresenter<SearchUserView> {
    private var mView: SearchUserView? = null

    override fun attachView(view: SearchUserView) {
        mView = view
    }

    override fun detachView() {
        mView = null
    }

    fun searchUsers(q: String, perPage: Int = 15, page: Int = 1) {
        mView?.onLoading()
        val call = ApiClient().makeService().searchUsers(q, perPage, page)
        call.enqueue(object : Callback<UsersModel> {
            override fun onResponse(call: Call<UsersModel>, response: Response<UsersModel>) {
                mView?.onLoading(false)
                if (response.isSuccessful) {
                    mView?.onSuccess(response.body())
                } else {
                    when (response.code()) {
                        500 -> mView?.onError("Internal server error")
                        else -> {
                            val error = getError<ErrorResponse>(response.errorBody()?.string())
                            mView?.onError(error?.message)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<UsersModel>, t: Throwable) {
                mView?.onLoading(false)
                mView?.onError(t.message)
            }

        })
    }

}


