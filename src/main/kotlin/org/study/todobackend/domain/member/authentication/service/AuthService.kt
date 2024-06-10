package org.study.todobackend.domain.member.authentication.service

import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.study.todobackend.common.dto.IdResponse
import org.study.todobackend.common.security.JwtTokenProvider
import org.study.todobackend.domain.member.authentication.dto.LoginRequest
import org.study.todobackend.domain.member.authentication.dto.LoginResponse
import org.study.todobackend.domain.member.authentication.dto.SignUpRequest
import org.study.todobackend.domain.member.model.MemberModel
import org.study.todobackend.domain.member.model.toIdResponse
import org.study.todobackend.domain.member.repository.MemberRepository

@Service
class AuthService(
    private val authenticationManager: AuthenticationManager,
    private val jwtTokenProvider: JwtTokenProvider,
    private val passwordEncoder: PasswordEncoder,
    private val memberRepository: MemberRepository,
) {
    @Transactional
    fun signUp(request: SignUpRequest): IdResponse {
        val (email, password) = request

        if (memberRepository.existsByEmail(email)) throw IllegalArgumentException("Email already exists")

        val member = MemberModel.of(
            email = email,
            password = passwordEncoder.encode(password),
            loginType = null,
        )
        return memberRepository.save(member).toIdResponse()
    }

    @Transactional
    fun login(request: LoginRequest): LoginResponse {
        authenticationManager.authenticate(UsernamePasswordAuthenticationToken(request.email, request.password))
        return LoginResponse(token = jwtTokenProvider.generateToken(request.email))
    }
}