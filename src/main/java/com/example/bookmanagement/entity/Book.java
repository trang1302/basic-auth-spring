package com.example.bookmanagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String filePath;
    private LocalDateTime uploadDate;
    private boolean isActive = true;
    
    @ManyToOne
    @JoinColumn(name = "uploaded_by")
    private User uploadedBy;
}