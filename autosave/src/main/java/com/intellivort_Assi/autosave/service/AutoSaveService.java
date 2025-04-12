package com.intellivort_Assi.autosave.service;

import com.intellivort_Assi.autosave.dto.*;
import com.intellivort_Assi.autosave.entity.*;
import com.intellivort_Assi.autosave.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class AutoSaveService {

    private final StudentRepository studentRepo;
    private final TestRepository testRepo;
    private final QuestionRepository questionRepo;
    private final StudentAnswerRepository answerRepo;

    public void saveResponses(AutoSaveRequest request) {
        Student student = studentRepo.findById(request.getStudentId()).orElseThrow(() ->
                new NoSuchElementException("Student not found with ID: " + request.getStudentId()));

        Test test = testRepo.findById(request.getTestId()).orElseThrow(() ->
                new NoSuchElementException("Test not found with ID: " + request.getTestId()));

        for (QuestionResponse qr : request.getResponses()) {
            Question question = questionRepo.findById(qr.getQuestionId()).orElseThrow(() ->
                    new NoSuchElementException("Question not found with ID: " + qr.getQuestionId()));

            StudentAnswer answer = answerRepo.findByStudentIdAndTestIdAndQuestionId(
                    request.getStudentId(), request.getTestId(), qr.getQuestionId()
            ).orElse(new StudentAnswer(null, student, test, question, null, null, null));

            answer.setAnswer(qr.getAnswer());
            answer.setStatus(qr.getStatus());
            answer.setSavedAt(LocalDateTime.now());
            answerRepo.save(answer);
        }
    }
}
