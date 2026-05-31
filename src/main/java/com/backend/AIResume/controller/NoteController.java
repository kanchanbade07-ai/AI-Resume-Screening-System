package com.backend.AIResume.controller;

import com.backend.AIResume.dto.NoteRequest;
import com.backend.AIResume.model.Note;
import com.backend.AIResume.service.NoteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

    @RestController
    @RequestMapping("/note")
    public class NoteController {

        @Autowired
        private NoteService noteService;

        @PostMapping("/save")
        public Note saveNote(@RequestBody NoteRequest request) {

            return noteService.saveNote(
                    request.getResumeId(),
                    request.getNote()
            );
        }

        @GetMapping("/resume/{resumeId}")
        public List<Note> getNotesByResumeId(
                @PathVariable Long resumeId) {

            return noteService.getNotesByResumeId(resumeId);
        }

        @GetMapping("/all")
        public List<Note> getAllNotes() {

            return noteService.getAllNotes();
        }
    }

