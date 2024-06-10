package org.study.todobackend.domain.member.authentication.controller

import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.ModelAndView
import org.study.todobackend.domain.member.authentication.dto.LoginRequest
import org.study.todobackend.domain.member.authentication.dto.LoginResponse
import org.study.todobackend.domain.member.authentication.dto.OAuthSignUpRequest
import org.study.todobackend.domain.member.authentication.service.NaverOAuthServiceImpl

@RequestMapping("/oauth2")
@RestController
class NaverOAuthController(
    private val naverOAuthServiceImpl: NaverOAuthServiceImpl,
) {
    @GetMapping("/naver/login")
    fun login(): ModelAndView {
        val url = naverOAuthServiceImpl.getNaverAuthrorizeUrl("authorize")
        return ModelAndView("redirect:$url")
    }

    @Operation(hidden = true)
    @GetMapping("/naver/success")
    fun loginSuccess(
        @RequestParam state: String,
        @RequestParam code: String?,
        @RequestParam error: String?,
        @RequestParam(name = "error_description") errorDescription: String?,
    ): LoginResponse {
        if (error != null) throw IllegalStateException(errorDescription!!)
        val tokenResponse = naverOAuthServiceImpl.getNaverTokenResponse("token", code!!)

        val (id, email) = naverOAuthServiceImpl.getNaverUserData(tokenResponse.accessToken)
        val password = id.substring(0 until 20)
        if (naverOAuthServiceImpl.checkSignedUp(email) == false) {
            naverOAuthServiceImpl.signup(
                OAuthSignUpRequest(
                    email = email,
                    password = password,
                    loginType = "Naver"
                )
            )
        }
        return naverOAuthServiceImpl.login(LoginRequest(email, password))
    }
}