package org.study.todobackend.domain.member.authentication.dto

data class LoginRequest(
    val email: String,
    val password: String,
)
