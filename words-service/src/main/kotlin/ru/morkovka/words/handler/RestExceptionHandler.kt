package ru.morkovka.words.handler

import org.apache.commons.lang3.exception.ExceptionUtils.getStackTrace
import org.springframework.core.Ordered
import org.springframework.core.annotation.AnnotationUtils
import org.springframework.core.annotation.Order
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import ru.morkovka.words.entity.ApiError
import javax.persistence.EntityNotFoundException
import javax.servlet.http.HttpServletRequest

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
class RestExceptionHandler : ResponseEntityExceptionHandler() {

    override fun handleHttpMessageNotReadable(
        ex: HttpMessageNotReadableException,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {
        return buildResponseEntity(
            ApiError(
                status = HttpStatus.BAD_REQUEST.value(),
                error = HttpStatus.BAD_REQUEST,
                message = "Malformed JSON request: ${ex.localizedMessage}"
            )
        )
    }

    @ExceptionHandler(value = [Exception::class])
    @Throws(Exception::class)
    fun defaultErrorHandler(request: HttpServletRequest, ex: Exception): ResponseEntity<Any>? {
        // If the exception is annotated with @ResponseStatus rethrow it and let
        // the framework handle it - like the OrderNotFoundException example
        // at the start of this post.
        // AnnotationUtils is a Spring Framework utility class.
        if (AnnotationUtils.findAnnotation(ex.javaClass, ResponseStatus::class.java) != null) throw ex

        // Otherwise setup and send the api error
        logger.warn(getStackTrace(ex))
        return buildResponseEntity(
            ApiError(
                status = HttpStatus.INTERNAL_SERVER_ERROR,
                ex = ex,
                path = request.requestURI
            )
        )
    }

    @ExceptionHandler(JpaObjectRetrievalFailureException::class, EntityNotFoundException::class)
    fun handleEntityNotFoundException(request: HttpServletRequest, ex: EntityNotFoundException): ResponseEntity<Any> {
        logger.warn(getStackTrace(ex))

        return buildResponseEntity(
            ApiError(
                status = HttpStatus.NOT_FOUND,
                message = "Unable to find object: ${ex.localizedMessage}",
                path = request.requestURI
            )
        )
    }

    @ExceptionHandler(HttpClientErrorException::class)
    protected fun handleHttpClientErrorException(
        request: HttpServletRequest,
        ex: HttpClientErrorException
    ): ResponseEntity<Any> {
        logger.warn("HttpClientErrorException: statusCode [${ex.statusCode}], statusText [${ex.statusText}], body: [${ex.responseBodyAsString}]")

        when (ex.statusCode) {
            HttpStatus.FORBIDDEN, HttpStatus.UNAUTHORIZED, HttpStatus.BAD_REQUEST, HttpStatus.NOT_FOUND, HttpStatus.UNSUPPORTED_MEDIA_TYPE
            -> {
                logger.warn(ex.printStackTrace())
                return buildResponseEntity(
                    ApiError(
                        status = ex.statusCode,
                        //message = "Unable to find object: ${ex.localizedMessage}",
                        message = ex.responseBodyAsString,
                        path = request.requestURI
                    )
                )
            }
            else -> {
                // don't like it,
                // because it will return to defaultErrorHandler and again to handleHttpClientErrorException -> loop
                throw ex
            }
        }
    }

    private fun buildResponseEntity(apiError: ApiError) = ResponseEntity<Any>(apiError, apiError.error)
}