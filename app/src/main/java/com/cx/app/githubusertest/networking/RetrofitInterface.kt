package com.cx.app.githubusertest.networking

import com.cx.app.githubusertest.model.UsersModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author kasmadi
 * @date 1/15/21
 */
interface RetrofitInterface {
    @GET("search/users")
    fun searchUsers(
        @Query("q") query: String,
        @Query("per_page") perPage: Int,
        @Query("page") page: Int,
    ): Call<UsersModel>
}