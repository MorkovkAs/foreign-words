package ru.morkovka.words

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import ru.morkovka.words.interceptor.LogRequestInterceptor

@Configuration
@ComponentScan(basePackages = ["ru.morkovka.words"])
class AppConfig : WebMvcConfigurer {
    @Autowired
    lateinit var logRequestInterceptor: LogRequestInterceptor

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(logRequestInterceptor)
    }
}