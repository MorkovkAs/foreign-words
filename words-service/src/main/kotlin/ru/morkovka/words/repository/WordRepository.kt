package ru.morkovka.words.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import ru.morkovka.words.entity.Word

interface WordRepository : JpaRepository<Word, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM word ORDER BY random() LIMIT :n")
    fun getRandomN(@Param("n") n: Int): MutableList<Word>
}