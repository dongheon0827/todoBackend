package org.study.todobackend.domain.card.model

import jakarta.persistence.*
import org.study.todobackend.domain.card.dto.CardResponse
import org.study.todobackend.domain.comment.model.CommentModel
import org.study.todobackend.domain.todo.model.TodoModel

@Entity
@Table(name = "card")
class CardModel(
    @Column(name = "title")
    var title: String,

    @Column(name = "description")
    var description: String? = null,

    @Column(name = "date")
    var date: String,

    @Column(name = "name")
    var name: String,

    @Column(name = "status")
    var status: Boolean,

    @OneToMany(mappedBy = "card", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    var comments: MutableList<CommentModel> = mutableListOf(),

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "todo_id")
    var todo: TodoModel
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    fun complete() {
        status = true
    }

    fun isComplete(): Boolean {
        return status == true
    }

    fun isIncomplete(): Boolean {
        return status == false
    }

    fun addComment(comment: CommentModel) {
        comments.add(comment)
    }

    fun removeComment(comment: CommentModel) {
        comments.remove(comment)
    }
}

fun CardModel.toResponse(): CardResponse {
    return CardResponse(
        id = id!!,
        title = title,
        description = description,
        date = date,
        name = name,
        status = status
    )
}