package com.backend.AIResume.repository;
import java.util.List;

import com.backend.AIResume.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

    public interface NoteRepository
            extends JpaRepository<Note, Long> {
        List<Note> findByResumeId(Long resumeId);

    }

