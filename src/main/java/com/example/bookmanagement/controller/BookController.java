package com.example.bookmanagement.controller;

import com.example.bookmanagement.dto.BookDto;
import com.example.bookmanagement.entity.Book;
import com.example.bookmanagement.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/books")
@CrossOrigin("*")
@Tag(name = "Books", description = "Book Management API")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    @Operation(summary = "Get all active books")
    public ResponseEntity<List<BookDto>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllActiveBooks());
    }

    @GetMapping("/search")
    @Operation(summary = "Search for books by name")
    public ResponseEntity<List<BookDto>> searchBooks(@RequestParam String keyword) {
        return ResponseEntity.ok(bookService.searchBooks(keyword));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get book by ID")
    public ResponseEntity<?> getBookById(@PathVariable Long id) {
        return bookService.getBookById(id)
                .map(book -> {
                    if (!book.isActive()) {
                        return ResponseEntity.notFound().build();
                    }
                    return ResponseEntity.ok(book);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/read")
    @Operation(summary = "Read a book file")
    public ResponseEntity<?> readBook(@PathVariable Long id) {
        try {
            Book book = bookService.getBookById(id)
                    .orElseThrow(() -> new RuntimeException("Book not found"));
            
            if (!book.isActive()) {
                return ResponseEntity.notFound().build();
            }
            
            Path path = Paths.get(book.getFilePath());
            Resource resource = new UrlResource(path.toUri());
            
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error reading book: " + e.getMessage());
        }
    }

    @PostMapping
    @Operation(summary = "Upload a new book")
    public ResponseEntity<?> uploadBook(
            @RequestParam("name") String name,
            @RequestParam("file") MultipartFile file,
            Authentication authentication) {
        try {
            Book book = bookService.uploadBook(name, file, authentication.getName());
            return ResponseEntity.ok(book);
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Error uploading book: " + e.getMessage());
        }
    }
}