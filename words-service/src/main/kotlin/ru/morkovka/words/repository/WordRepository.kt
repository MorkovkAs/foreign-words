package ru.morkovka.words.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.morkovka.words.entity.Word

interface WordRepository : JpaRepository<Word, Long>