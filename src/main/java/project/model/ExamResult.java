package project.model;

import java.math.BigDecimal;

public class ExamResult {
    private int examSessionID;
    private int studentID;
    private BigDecimal theoryScore;
    private BigDecimal practicalScore;
    private BigDecimal averageScore;

    public ExamResult() {}

    public ExamResult(int examSessionID, BigDecimal theoryScore, BigDecimal practicalScore, BigDecimal averageScore) {
        this.examSessionID = examSessionID;
        this.theoryScore = theoryScore;
        this.practicalScore = practicalScore;
        this.averageScore = averageScore;
    }

    public ExamResult(int examSessionId, int studentId, BigDecimal theoryScore, BigDecimal practicalScore,
                      BigDecimal averageScore) {
        this.examSessionID = examSessionId;
        this.studentID = studentId;
        this.theoryScore = theoryScore;
        this.practicalScore = practicalScore;
        this.averageScore = averageScore;
    }

    public int getExamSessionID() {
        return examSessionID;
    }

    public void setExamSessionID(int examSessionID) {
        this.examSessionID = examSessionID;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public BigDecimal getTheoryScore() {
        return theoryScore;
    }

    public void setTheoryScore(BigDecimal theoryScore) {
        this.theoryScore = theoryScore;
    }

    public BigDecimal getPracticalScore() {
        return practicalScore;
    }

    public void setPracticalScore(BigDecimal practicalScore) {
        this.practicalScore = practicalScore;
    }

    public BigDecimal getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(BigDecimal averageScore) {
        this.averageScore = averageScore;
    }
}
