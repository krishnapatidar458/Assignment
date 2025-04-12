package com.intellivort_Assi.autosave.repository;

import com.intellivort_Assi.autosave.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {}
