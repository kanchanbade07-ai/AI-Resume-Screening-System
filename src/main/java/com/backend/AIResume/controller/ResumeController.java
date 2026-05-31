package com.backend.AIResume.controller;

import com.backend.AIResume.model.Resume;
import com.backend.AIResume.service.ResumeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

import com.backend.AIResume.dto.JobRequest;

import com.backend.AIResume.dto.MatchResponse;
import com.backend.AIResume.dto.ResumeRankDTO;

import com.backend.AIResume.dto.DashboardDTO;
import com.backend.AIResume.dto.RecentResumeDTO;

@RestController
@RequestMapping("/resume")
public class ResumeController {

    @Autowired
    ResumeService resumeService;

    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> downloadResume(
            @PathVariable Long id) throws Exception {

        return resumeService.downloadResume(id);
    }

    @PostMapping("/rank")
    public List<ResumeRankDTO> rankResumes(
            @RequestBody String jobDescription) {

        return resumeService.rankResumes(jobDescription);
    }


    @PostMapping("/match/{id}")
    public MatchResponse matchResume(
            @PathVariable Long id,
            @RequestBody JobRequest request) {

        return resumeService.matchResume(
                id,
                request.getJobDescription()
        );
    }


    @GetMapping("/dashboard")
    public DashboardDTO getDashboardStats() {

        return resumeService.getDashboardStats();
    }

    @GetMapping("/recent")
    public List<RecentResumeDTO> getRecentResumes() {

        return resumeService.getRecentResumes();
    }

    // UPLOAD RESUME
    @PostMapping("/upload")
    public Resume uploadResume(
            @RequestParam("file") MultipartFile file)
            throws Exception {

        System.out.println("UPLOAD API HIT");

        return resumeService.uploadResume(file);
    }

    // GET ALL RESUMES
    @GetMapping("/all")
    public List<Resume> getAllResumes() {

        return resumeService.getAllResumes();
    }
}
