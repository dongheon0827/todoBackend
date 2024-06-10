package org.study.todobackend.domain.member.model

import jakarta.persistence.*
import org.study.todobackend.common.dto.IdResponse

@Entity
@Table(name = "member")
class MemberModel(
    @Column(name = "email", nullable = false, unique = true)
    var email: String,

    @Column(name = "password", nullable = false)
    var password: String,

    @Column(name = "login_type")
    var loginType: String?,

    ) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    companion object {
        fun of(email: String, password: String, loginType: String?): MemberModel {
            return MemberModel(
                email = email,
                password = password,
                loginType = loginType
            )
        }
    }

}

fun MemberModel.toIdResponse(): IdResponse {
    return IdResponse(
        id = id!!,
    )
}