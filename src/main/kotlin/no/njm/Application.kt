package no.njm

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.web.client.RestTemplate

@Suppress("SpringJavaInjectionPointsAutowiringInspection")
@SpringBootApplication
class Application {
    /**
     * The auto-configured RestTemplateBuilder looks for ClientHttpRequestFactory implementations on the classpath.
     *
     * @see org.springframework.boot.web.client.RestTemplateBuilder
     * @see org.springframework.boot.web.client.ClientHttpRequestFactorySupplier
     */
    @Bean
    fun restTemplate(restTemplateBuilder: RestTemplateBuilder): RestTemplate? {
        return restTemplateBuilder.detectRequestFactory(false).rootUri(BASE_URL).build()
    }
}

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
