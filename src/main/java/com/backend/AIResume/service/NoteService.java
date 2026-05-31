package com.backend.AIResume.service;

import com.backend.AIResume.model.Note;
import com.backend.AIResume.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;



    @Service
    public class NoteService {

        @Autowired
        private NoteRepository noteRepository;

        public Note saveNote(Long resumeId, String noteText) {

            Note note = new Note();

            note.setResumeId(resumeId);
            note.setNote(noteText);

            return noteRepository.save(note);
        }
        public List<Note> getAllNotes() {
            return noteRepository.findAll();
        }
        public List<Note> getNotesByResumeId(Long resumeId) {

            return noteRepository.findByResumeId(resumeId);
        }
    }

