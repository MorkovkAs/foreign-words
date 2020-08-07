package ru.morkovka.words.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import ru.morkovka.words.entity.Word
import ru.morkovka.words.service.WordService

@RestController
@CrossOrigin
@RequestMapping("/api/word")
class WordController {

    @Autowired
    private lateinit var wordService: WordService

    @GetMapping
    fun index() = wordService.getAll()

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody word: Word) = wordService.create(word)

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.FOUND)
    fun read(@PathVariable id: Long) = wordService.get(id)

    @PutMapping("{id}")
    fun update(@PathVariable id: Long, @RequestBody word: Word) = wordService.update(id, word)

    @DeleteMapping("{id}")
    fun delete(@PathVariable id: Long) = wordService.delete(id)

    @GetMapping("random/{n}")
    fun getRandomN(@PathVariable n: Int) = wordService.getRandomN(n)
}