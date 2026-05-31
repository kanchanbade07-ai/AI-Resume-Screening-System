package com.backend.AIResume.dto;

public class ResumeRankDTO {

    private Long id;
    private String candidateName;
    private String fileName;
    private double matchPercentage;
    private int rank;

    public ResumeRankDTO() {
    }

    public ResumeRankDTO(Long id,
                         String candidateName,
                         String fileName,
                         double matchPercentage) {

        this.id = id;
        this.candidateName = candidateName;
        this.fileName = fileName;
        this.matchPercentage = matchPercentage;
    }
    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
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

    public double getMatchPercentage() {
        return matchPercentage;
    }

    public void setMatchPercentage(double matchPercentage) {
        this.matchPercentage = matchPercentage;
    }
}