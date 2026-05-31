package com.backend.AIResume.service;

import com.backend.AIResume.model.Resume;
import com.backend.AIResume.repository.ResumeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.nio.file.Path;
import java.nio.file.Paths;

import com.backend.AIResume.dto.MatchResponse;
import com.backend.AIResume.dto.ResumeRankDTO;

import java.util.ArrayList;
import com.backend.AIResume.dto.DashboardDTO;
import com.backend.AIResume.dto.RecentResumeDTO;

@Service
public class ResumeService {

    @Autowired
    ResumeRepository resumeRepository;


    public ResponseEntity<Resource> downloadResume(Long id)
            throws Exception {

        // FIND RESUME FROM DB
        Resume resume = resumeRepository.findById(id).get();

        // FILE PATH
        Path path = Paths.get(resume.getFilePath());

        // CONVERT FILE TO RESOURCE
        Resource resource = new UrlResource(path.toUri());

        // RETURN FILE
        return ResponseEntity.ok()

                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\""
                                + resource.getFilename() + "\""
                )

                .body(resource);
    }


    public List<ResumeRankDTO> rankResumes(String jobDescription) {

        List<Resume> resumes = resumeRepository.findAll();

        List<ResumeRankDTO> rankedList = new ArrayList<>();

        String[] skills = {
                "java",
                "spring",
                "spring boot",
                "hibernate",
                "mysql",
                "sql",
                "react",
                "javascript"
        };

        for (Resume resume : resumes) {

            String text = "";

            if (resume.getExtractedText() != null) {
                text = resume.getExtractedText().toLowerCase();
            }

            int matched = 0;

            for (String skill : skills) {

                if (text.contains(skill)
                        &&
                        jobDescription.toLowerCase().contains(skill)) {

                    matched++;
                }
            }

            double percentage =
                    ((double) matched / skills.length) * 100;

            String candidateName =
                    resume.getFileName()
                            .replace(".pdf", "")
                            .replace("Resume", "")
                            .trim();

            rankedList.add(
                    new ResumeRankDTO(
                            resume.getId(),
                            candidateName,
                            resume.getFileName(),
                            percentage
                    )
            );
        }

        rankedList.sort((a, b) ->
                Double.compare(
                        b.getMatchPercentage(),
                        a.getMatchPercentage()
                ));

        for (int i = 0; i < rankedList.size(); i++) {

            rankedList.get(i).setRank(i + 1);
        }

        return rankedList;
    }


    public DashboardDTO getDashboardStats() {

        List<Resume> resumes = resumeRepository.findAll();

        int totalResumes = resumes.size();

        double topMatchPercentage = 0;

        for (Resume resume : resumes) {

            String text = "";

            if (resume.getExtractedText() != null) {
                text = resume.getExtractedText().toLowerCase();
            }

            int score = 0;

            String[] skills = {
                    "java",
                    "spring",
                    "spring boot",
                    "hibernate",
                    "mysql",
                    "sql",
                    "react",
                    "javascript"
            };

            for (String skill : skills) {

                if (text.contains(skill)) {
                    score++;
                }
            }

            double percentage =
                    ((double) score / skills.length) * 100;

            if (percentage > topMatchPercentage) {
                topMatchPercentage = percentage;
            }
        }

        return new DashboardDTO(
                totalResumes,
                totalResumes,
                topMatchPercentage
        );
    }


    public List<RecentResumeDTO> getRecentResumes() {

        List<Resume> resumes = resumeRepository.findAll();

        List<RecentResumeDTO> recentResumes = new ArrayList<>();

        for (Resume resume : resumes) {

            String candidateName =
                    resume.getFileName()
                            .replace(" Resume.pdf", "");

            recentResumes.add(
                    new RecentResumeDTO(
                            resume.getId(),
                            candidateName,
                            resume.getFileName()
                    )
            );
        }

        return recentResumes;
    }



    public MatchResponse matchResume(Long id, String jobDescription) {

        Resume resume = resumeRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Resume not found"));

        String text = "";

        if (resume.getExtractedText() != null) {
            text = resume.getExtractedText().toLowerCase();
        }

        jobDescription = jobDescription.toLowerCase();

        int matchCount = 0;

        String[] skills = {
                "java",
                "spring",
                "spring boot",
                "hibernate",
                "mysql",
                "sql",
                "react",
                "javascript",
                "docker",
                "kubernetes",
                "aws",
                "microservices",
                "html",
                "css"
        };

        List<String> missingSkills = new java.util.ArrayList<>();

        for (String skill : skills) {

            if (text.contains(skill)
                    &&
                    jobDescription.contains(skill)) {

                matchCount++;
            }

            else if (jobDescription.contains(skill)) {

                missingSkills.add(skill);
            }
        }

        double percentage =
                Math.round(((double) matchCount / skills.length) * 100 * 100.0) / 100.0;

        return new MatchResponse(percentage, missingSkills);
    }

    public Resume uploadResume(MultipartFile file) throws Exception {

        // CREATE uploads FOLDER
        File uploadDir = new File("uploads");

        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        // FILE PATH
        String filePath =
                uploadDir.getAbsolutePath()
                        + File.separator
                        + file.getOriginalFilename();

        // SAVE FILE
        file.transferTo(new File(filePath));

        // EXTRACT TEXT FROM PDF
        PDDocument document =
                PDDocument.load(new File(filePath));

        PDFTextStripper pdfStripper =
                new PDFTextStripper();

        String extractedText =
                pdfStripper.getText(document);

        document.close();

        // SAVE DATA IN DATABASE
        Resume resume = new Resume();

        resume.setFileName(file.getOriginalFilename());

        resume.setFilePath(filePath);

        resume.setExtractedText(extractedText);

        return resumeRepository.save(resume);
    }

    // GET ALL RESUMES
    public List<Resume> getAllResumes() {

        return resumeRepository.findAll();
    }
}