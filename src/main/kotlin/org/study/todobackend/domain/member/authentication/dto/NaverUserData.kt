package org.study.todobackend.domain.member.authentication.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class NaverUserDataResponse(
    @JsonProperty("resultcode")
    val resultcode: String,
    val message: String,
    val response: NaverUserData,
)

data class NaverUserData(
    val id: String,
    val email: String,
)