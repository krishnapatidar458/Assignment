package com.intellivort_Assi.autosave.dto;

import lombok.*;

@Data
public class QuestionResponse {
    private Long questionId;
    private String answer;
    private String status; // Attempted, Skipped, Review
}