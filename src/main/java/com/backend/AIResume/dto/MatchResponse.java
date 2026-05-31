package com.backend.AIResume.dto;

import java.util.List;

    public class MatchResponse {

        private double matchPercentage;
        private List<String> missingSkills;

        public MatchResponse(double matchPercentage, List<String> missingSkills) {
            this.matchPercentage = matchPercentage;
            this.missingSkills = missingSkills;
        }

        public double getMatchPercentage() {
            return matchPercentage;
        }

        public void setMatchPercentage(double matchPercentage) {
            this.matchPercentage = matchPercentage;
        }

        public List<String> getMissingSkills() {
            return missingSkills;
        }

        public void setMissingSkills(List<String> missingSkills) {
            this.missingSkills = missingSkills;
        }
    }

