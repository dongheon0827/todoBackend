package org.study.todobackend.domain.member.authentication.service

import org.study.todobackend.common.dto.IdResponse
import org.study.todobackend.domain.member.authentication.dto.LoginRequest
import org.study.todobackend.domain.member.authentication.dto.LoginResponse
import org.study.todobackend.domain.member.authentication.dto.OAuthSignUpRequest

interface OAuthService {
    fun signup(request: OAuthSignUpRequest):IdResponse
    fun login(request: LoginRequest): LoginResponse
}