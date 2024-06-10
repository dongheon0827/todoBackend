package org.study.todobackend.domain.member.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.study.todobackend.domain.member.model.MemberModel

interface MemberRepository : JpaRepository<MemberModel, Long> {
    fun findByEmail(email: String): MemberModel?

    fun existsByEmail(email: String): Boolean
}