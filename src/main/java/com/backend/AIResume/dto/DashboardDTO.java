package com.backend.AIResume.dto;

    public class DashboardDTO {

        private int totalResumes;
        private double topMatchPercentage;
        private long totalCandidates;

        public DashboardDTO(int totalResumes,
                            long totalCandidates,
                            double topMatchPercentage) {

            this.totalResumes = totalResumes;
            this.totalCandidates = totalCandidates;
            this.topMatchPercentage = topMatchPercentage;
        }

        public long getTotalCandidates() {
            return totalCandidates;
        }

        public void setTotalCandidates(long totalCandidates) {
            this.totalCandidates = totalCandidates;
        }

        public int getTotalResumes() {
            return totalResumes;
        }

        public void setTotalResumes(int totalResumes) {
            this.totalResumes = totalResumes;
        }

        public double getTopMatchPercentage() {
            return topMatchPercentage;
        }

        public void setTopMatchPercentage(double topMatchPercentage) {
            this.topMatchPercentage = topMatchPercentage;
        }
    }

