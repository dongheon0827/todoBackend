package org.study.todobackend.domain.comment.model

import jakarta.persistence.*
import org.study.todobackend.domain.card.model.CardModel
import org.study.todobackend.domain.comment.dto.CommentResponse

@Entity
@Table(name = "comment")
class CommentModel(

    @Column(name = "name")
    var name: String,

    @Column(name = "password")
    var password: String,

    @Column(name = "comments")
    var comments: String,

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "card_id")
    var card: CardModel
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}

fun CommentModel.toResponse(): CommentResponse {
    return CommentResponse(
        id = id!!,
        name = name,
        password = password,
        comments = comments,
    )
}