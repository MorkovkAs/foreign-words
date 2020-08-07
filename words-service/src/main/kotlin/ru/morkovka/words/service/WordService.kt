package ru.morkovka.words.service

import ru.morkovka.words.entity.Word

interface WordService {
    fun getAll(): List<Word>

    fun create(word: Word): Word

    fun get(id: Long): Word

    fun update(id: Long, word: Word): Word

    fun delete(id: Long)

    fun getRandomN (n: Int): MutableList<Word>
}