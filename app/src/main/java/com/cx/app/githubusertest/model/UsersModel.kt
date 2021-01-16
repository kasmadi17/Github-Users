package com.cx.app.githubusertest.model

import com.google.gson.annotations.SerializedName

/**
 * @author kasmadi
 * @date 1/15/21
 */
class UsersModel(
	@SerializedName("total_count")
	val total_count: Int,
	@SerializedName("incomplete_results")
	val incomplete_results: Boolean,
	@SerializedName("items")
	val items: List<Items>

)