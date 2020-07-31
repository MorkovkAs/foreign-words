package ru.morkovka.words.service.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.morkovka.words.entity.Word
import ru.morkovka.words.repository.WordRepository
import ru.morkovka.words.service.WordService

@Service
class WordServiceImpl : WordService {
    @Autowired
    private lateinit var wordRepository: WordRepository

    override fun getAll(): List<Word> =
        wordRepository.findAll()

    override fun create(word: Word): Word =
        wordRepository.save(word)

    override fun get(id: Long): Word =
        wordRepository.getOne(id)

    override fun update(id: Long, word: Word): Word =
        wordRepository.save(word.copy(id = id))

    override fun delete(id: Long) =
        wordRepository.deleteById(id)


}