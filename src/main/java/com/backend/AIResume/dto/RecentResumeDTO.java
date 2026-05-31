package com.backend.AIResume.dto;

    public class RecentResumeDTO {

        private Long id;
        private String candidateName;
        private String fileName;

        public RecentResumeDTO() {
        }

        public RecentResumeDTO(Long id,
                               String candidateName,
                               String fileName) {
            this.id = id;
            this.candidateName = candidateName;
            this.fileName = fileName;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getCandidateName() {
            return candidateName;
        }

        public void setCandidateName(String candidateName) {
            this.candidateName = candidateName;
        }

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }
    }

