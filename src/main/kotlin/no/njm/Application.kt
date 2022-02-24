package no.njm

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.web.client.RestTemplate

@Suppress("SpringJavaInjectionPointsAutowiringInspection")
@SpringBootApplication
class Application : CommandLineRunner {

    @Value("\${server.port}")
    private val serverPort = 0

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

    private val log = logger()

    override fun run(vararg args: String?) {
        log.info("Application is listening on port $serverPort.")
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}
