package com.intellivort_Assi.autosave.repository;

import com.intellivort_Assi.autosave.entity.StudentAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentAnswerRepository extends JpaRepository<StudentAnswer, Long> {
    Optional<StudentAnswer> findByStudentIdAndTestIdAndQuestionId(Long studentId, Long testId, Long questionId);
}
