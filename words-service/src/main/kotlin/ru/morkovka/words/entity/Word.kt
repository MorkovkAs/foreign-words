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
    val name: String,

    @Column
    val translation: String,

    @Column(name = "part_of_speech")
    @Enumerated(EnumType.STRING)
    val partOfSpeech: PartOfSpeech,

    @Column
    @Enumerated(EnumType.STRING)
    val gender: Gender
)