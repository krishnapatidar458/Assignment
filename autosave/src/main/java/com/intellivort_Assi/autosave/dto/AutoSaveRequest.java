package com.intellivort_Assi.autosave.dto;

import lombok.*;
import java.util.List;

@Data
public class AutoSaveRequest {
    private Long studentId;
    private Long testId;
    private List<QuestionResponse> responses;
}