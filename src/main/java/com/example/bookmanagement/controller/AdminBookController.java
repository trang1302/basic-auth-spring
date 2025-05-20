package com.example.bookmanagement.controller;

import com.example.bookmanagement.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/books")
@CrossOrigin("*")
@Tag(name = "Admin Books", description = "Admin Book Management API")
public class AdminBookController {

    private final BookService bookService;

    public AdminBookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PutMapping("/{id}/status")
    @Operation(summary = "Activate or deactivate a book")
    public ResponseEntity<?> setBookStatus(@PathVariable Long id, @RequestParam boolean active) {
        try {
            return ResponseEntity.ok(bookService.setBookStatus(id, active));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}