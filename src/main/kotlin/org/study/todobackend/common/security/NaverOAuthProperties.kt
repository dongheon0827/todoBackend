package org.study.todobackend.common.security

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class NaverOAuthProperties(
    @Value("\${naver-oauth.clientId}")
    val clientId: String,
    @Value("\${naver-oauth.clientSecret}")
    val clientSecret: String,
    @Value("\${naver-oauth.redirectUri}")
    val redirectUri: String,
    @Value("\${naver-oauth.baseUrl}")
    val baseUrl: String,
)