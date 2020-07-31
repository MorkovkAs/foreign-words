package ru.morkovka.words.entity

import com.fasterxml.jackson.annotation.JsonFormat
import org.springframework.http.HttpStatus
import java.time.LocalDateTime

/**
 * Error handling by
 * https://stackoverflow.com/questions/38117717/what-is-the-best-way-to-return-different-types-of-responseentity-in-spring-mvc-o
 * https://www.toptal.com/java/spring-boot-rest-api-error-handling
 */
data class ApiError @JvmOverloads constructor(
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    val timestamp: LocalDateTime = LocalDateTime.now(),
    val status: Int,
    val error: HttpStatus,
    val message: String = "Unexpected Error",
    val path: String? = null
) {
    constructor(status: HttpStatus, ex: Throwable, path: String) : this(
        status = status.value(),
        error = status,
        message = ex.localizedMessage,
        path = path
    )

    constructor(status: HttpStatus, message: String, path: String) : this(
        status = status.value(),
        error = status,
        message = message,
        path = path
    )
}