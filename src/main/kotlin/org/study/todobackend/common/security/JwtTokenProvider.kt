package org.study.todobackend.common.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtTokenProvider(
    @Value("\${jwt.secret}") private val secretKey: String,
    @Value("\${jwt.validity}") private val validityMilliseconds: Long,
    private val userDetailsService: UserDetailsService
) {
    // 토큰 생성
    fun generateToken(email: String): String {
        val now = Date()
        val validity = Date(now.time + validityMilliseconds)

        return Jwts.builder()
            .setSubject(email) // 식별자는 이메일
            .setIssuedAt(now) // 생성 시간
            .setExpiration(validity) // 만료시간은 생성시간 + 1시간
            .signWith(SignatureAlgorithm.HS256, secretKey) // HS256 알고리즘으로토큰화, secretKey로 서명
            .compact() // 위 정보들을 문자열로 토큰생성
    }

    // 토큰 유효성 검증
    fun validateToken(token: String): Boolean {
        return try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJwt(token) // secretKey 로 토큰 parsing 해서 해당 토큰의 정보를 추출
            true
        } catch (ex: Exception) { // 예외 발생시 false
            false
        }
    }

    fun getEmailFromToken(token: String): String {
        val claims: Claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).body
        return claims.subject
    }

    // 인가 부분에서 사용 할 토큰으로 인증객체 생성
    fun getAuthentication(token: String): Authentication {
        val email = getEmailFromToken(token)
        val userDetails = userDetailsService.loadUserByUsername(email)
        val authorities = userDetails.authorities.map { SimpleGrantedAuthority(it.authority) }

        return UsernamePasswordAuthenticationToken(userDetails, "", authorities)
    }
}