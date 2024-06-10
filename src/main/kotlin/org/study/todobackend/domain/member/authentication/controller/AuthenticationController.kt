package org.study.todobackend.domain.member.authentication.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.study.todobackend.domain.member.authentication.dto.LoginRequest
import org.study.todobackend.domain.member.authentication.dto.LoginResponse
import org.study.todobackend.domain.member.authentication.dto.SignUpRequest
import org.study.todobackend.domain.member.authentication.service.AuthService

@RestController
@RequestMapping("/auth")
class AuthenticationController(
    private val authService: AuthService
) {
    @PostMapping("/sign-up")
    fun signUp(@RequestBody signUpRequest: SignUpRequest): ResponseEntity<Unit> {
        authService.signUp(signUpRequest)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    @PostMapping("/login")
    fun login(@RequestBody loginRequest: LoginRequest): ResponseEntity<LoginResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(authService.login(loginRequest))
    }
}