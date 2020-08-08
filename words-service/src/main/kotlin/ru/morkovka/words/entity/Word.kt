package ru.morkovka.words.entity

import javax.persistence.*

@Entity
@Table(name = "word")
data class Word(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "word_id")
    val id: Long = 0,

    @Column
    var name: String,

    @Column
    var translation: String,

    @Column(name = "part_of_speech")
    @Enumerated(EnumType.STRING)
    var partOfSpeech: PartOfSpeech,

    @Column
    @Enumerated(EnumType.STRING)
    var gender: Gender?,

    @Column
    var counter: Int = 0
)