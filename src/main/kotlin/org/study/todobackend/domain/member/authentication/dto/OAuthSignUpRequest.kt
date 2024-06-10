package org.study.todobackend.domain.member.authentication.dto

data class OAuthSignUpRequest(
    val email: String,
    val password: String,
    val loginType: String,
)