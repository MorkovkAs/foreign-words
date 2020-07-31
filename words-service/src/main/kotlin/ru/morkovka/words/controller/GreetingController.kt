package ru.morkovka.words.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class GreetingController {

    @GetMapping("/greet")
    fun getGreeting() =
        "Hello from super app for memorizing foreign words!"
}