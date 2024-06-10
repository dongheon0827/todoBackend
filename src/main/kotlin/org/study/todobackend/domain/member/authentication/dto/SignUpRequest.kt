package org.study.todobackend.domain.member.authentication.dto

data class SignUpRequest(
    val email: String,
    val password: String,
)
