package com.backend.AIResume.repository;

import com.backend.AIResume.model.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

    public interface ResumeRepository
            extends JpaRepository<Resume, Long> {
    }

