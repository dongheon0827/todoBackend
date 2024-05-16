package org.study.todobackend.domain.card.model

import jakarta.persistence.*
import org.study.todobackend.domain.card.dto.CardResponse
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

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "todo_id")
    var todo: TodoModel
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}

fun CardModel.toResponse(): CardResponse {
    return CardResponse(
        id = id!!,
        title = title,
        description = description,
        date = date,
        name = name
    )
}