package com.backend.AIResume.repository;

import com.backend.AIResume.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

    public interface JobRepository extends JpaRepository<Job, Long> {

    }

