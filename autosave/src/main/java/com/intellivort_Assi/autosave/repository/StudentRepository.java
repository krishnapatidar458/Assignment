package com.intellivort_Assi.autosave.repository;

import com.intellivort_Assi.autosave.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {}

