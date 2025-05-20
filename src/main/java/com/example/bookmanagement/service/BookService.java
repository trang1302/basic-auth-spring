package com.example.bookmanagement.service;

import com.example.bookmanagement.dto.BookDto;
import com.example.bookmanagement.entity.Book;
import com.example.bookmanagement.entity.User;
import com.example.bookmanagement.repository.BookRepository;
import com.example.bookmanagement.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final String uploadDir;

    public BookService(BookRepository bookRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.uploadDir = "./uploads";
        createUploadDirectory();
    }

    private void createUploadDirectory() {
        try {
            Path path = Paths.get(uploadDir);
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not create upload directory", e);
        }
    }

    public List<BookDto> getAllActiveBooks() {
        return bookRepository.findByIsActiveTrue().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<BookDto> searchBooks(String keyword) {
        return bookRepository.findByNameContainingIgnoreCaseAndIsActiveTrue(keyword).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    public Book uploadBook(String name, MultipartFile file, String username) throws IOException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path filePath = Paths.get(uploadDir, fileName);
        Files.copy(file.getInputStream(), filePath);

        Book book = new Book();
        book.setName(name);
        book.setFilePath(filePath.toString());
        book.setUploadDate(LocalDateTime.now());
        book.setActive(true);
        book.setUploadedBy(user);

        return bookRepository.save(book);
    }

    public Book setBookStatus(Long id, boolean isActive) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        book.setActive(isActive);
        return bookRepository.save(book);
    }

    private BookDto convertToDto(Book book) {
        return new BookDto(
                book.getId(),
                book.getName(),
                book.getUploadDate(),
                book.getUploadedBy().getUsername()
        );
    }
}