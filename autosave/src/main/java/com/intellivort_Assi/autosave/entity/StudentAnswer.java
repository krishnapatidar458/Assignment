package com.intellivort_Assi.autosave.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Student student;

    @ManyToOne
    private Test test;

    @ManyToOne
    private Question question;

    private String answer;
    private String status; // Attempted, Skipped, Review
    private LocalDateTime savedAt;
}

