package project.model;

import java.math.BigDecimal;

public class ExamResult {
    private int examSessionId;
    private int studentId;
    private BigDecimal theoryScore;
    private BigDecimal practicalScore;

    public ExamResult() {}

    public ExamResult(int examSessionId, int studentId, BigDecimal theoryScore, BigDecimal practicalScore) {
        this.examSessionId = examSessionId;
        this.studentId = studentId;
        this.theoryScore = theoryScore;
        this.practicalScore = practicalScore;
    }

    public int getExamSessionId() {
        return examSessionId;
    }

    public void setExamSessionId(int examSessionId) {
        this.examSessionId = examSessionId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
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

}
