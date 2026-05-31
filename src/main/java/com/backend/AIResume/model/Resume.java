package com.backend.AIResume.model;
import jakarta.persistence.*;

    @Entity
    public class Resume {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String fileName;

        private String filePath;

        public void setExtractedText(String extractedText) {
            this.extractedText = extractedText;
        }

        public String getExtractedText() {
            return extractedText;
        }

        @Column(length = 10000)
        private String extractedText;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public String getFilePath() {
            return filePath;
        }

        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }
    }

