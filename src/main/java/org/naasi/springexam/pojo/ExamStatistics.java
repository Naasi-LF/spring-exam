package org.naasi.springexam.pojo;

import java.util.List;

public class ExamStatistics {
    private double averageScore;
    private double highestScore;
    private double lowestScore;
    private List<StudentExamResult> scoreDistribution;

    public ExamStatistics(double averageScore, double highestScore, double lowestScore, List<StudentExamResult> scoreDistribution) {
        this.averageScore = averageScore;
        this.highestScore = highestScore;
        this.lowestScore = lowestScore;
        this.scoreDistribution = scoreDistribution;
    }

    public double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(double averageScore) {
        this.averageScore = averageScore;
    }

    public double getHighestScore() {
        return highestScore;
    }

    public void setHighestScore(double highestScore) {
        this.highestScore = highestScore;
    }

    public double getLowestScore() {
        return lowestScore;
    }

    public void setLowestScore(double lowestScore) {
        this.lowestScore = lowestScore;
    }

    public List<StudentExamResult> getScoreDistribution() {
        return scoreDistribution;
    }

    public void setScoreDistribution(List<StudentExamResult> scoreDistribution) {
        this.scoreDistribution = scoreDistribution;
    }
}
