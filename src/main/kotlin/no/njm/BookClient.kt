package no.njm

import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class BookClient(private val restTemplate: RestTemplate) {

    private val log = logger()

    fun getBooks(): List<Book> {
        val responseEntity = restTemplate.getForEntity("$BASE_URL/books", Array<Book>::class.java)
        responseEntity.body?.let {
            return it.toList()
        }
        return listOf()
    }

    fun updateBook(id: Int, book: UpdateBook): Book? {
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON

        val httpEntity = HttpEntity(book, headers)
        val responseEntity = restTemplate.exchange("$BASE_URL/books/$id", HttpMethod.PATCH, httpEntity, Book::class.java)
        return responseEntity.body
    }
}

const val BASE_URL = "http://localhost:3000"

data class Book(
    val id: Int,
    val title: String,
    val author: String,
)

data class UpdateBook(
    val author: String,
    val title: String,
)
