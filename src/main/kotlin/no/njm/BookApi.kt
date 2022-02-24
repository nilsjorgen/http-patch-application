package no.njm

import com.fasterxml.jackson.annotation.JsonInclude
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class BookApi(private val bookClient: BookClient) {

    @GetMapping("/books")
    fun getBooks(): ResponseEntity<List<Book>> {
        return ResponseEntity.ok(bookClient.getBooks())
    }

    @PatchMapping("/books/{id}")
    fun newEmployee(@PathVariable id: Int?, @RequestBody updateBook: UpdateBook?): ResponseEntity<Any> {
        val updatedBook = bookClient.updateBook(id!!, updateBook!!)
        updatedBook?.let {
            return ResponseEntity.ok(updatedBook)
        }
        return ResponseEntity<Any>(HttpStatus.NOT_FOUND)
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    data class Product(
        var id: Int,
    )
}
