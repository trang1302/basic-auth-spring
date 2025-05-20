package com.example.bookmanagement.dto;

import java.time.LocalDateTime;

public class BookDto {
    private Long id;
    private String name;
    private LocalDateTime uploadDate;
    private String uploadedBy;

    // Constructor không tham số
    public BookDto() {
    }

    // Constructor có tất cả tham số
    public BookDto(Long id, String name, LocalDateTime uploadDate, String uploadedBy) {
        this.id = id;
        this.name = name;
        this.uploadDate = uploadDate;
        this.uploadedBy = uploadedBy;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(LocalDateTime uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getUploadedBy() {
        return uploadedBy;
    }

    public void setUploadedBy(String uploadedBy) {
        this.uploadedBy = uploadedBy;
    }

    @Override
    public String toString() {
        return "BookDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", uploadDate=" + uploadDate +
                ", uploadedBy='" + uploadedBy + '\'' +
                '}';
    }
}
