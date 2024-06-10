package org.study.todobackend.domain.member.authentication.service

import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.study.todobackend.domain.member.repository.MemberRepository

@Service
class GetUserDetailsService(private val memberRepository: MemberRepository) : UserDetailsService {
    override fun loadUserByUsername(email: String): UserDetails {
        val user = memberRepository.findByEmail(email) ?: throw UsernameNotFoundException("email not found")

        return User(
            user.email,
            user.password,
            emptyList(),
        )
    }
}