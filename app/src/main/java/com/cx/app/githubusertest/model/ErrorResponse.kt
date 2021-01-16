package com.cx.app.githubusertest.model

data class ErrorResponse(
    val documentation_url: String,
    val errors: List<Error>,
    val message: String
)