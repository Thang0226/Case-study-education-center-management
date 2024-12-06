package project.model;

public class ExamResult {
    private int examSessionId;
    private int studentId;
    private float theoryScore;
    private float practicalScore;
    private float averageScore;

    public ExamResult() {}

    public ExamResult(int examSessionId, int studentId, float theoryScore, float practicalScore, float averageScore) {
        this.examSessionId = examSessionId;
        this.studentId = studentId;
        this.theoryScore = theoryScore;
        this.practicalScore = practicalScore;
        this.averageScore = averageScore;
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

    public float getTheoryScore() {
        return theoryScore;
    }

    public void setTheoryScore(float theoryScore) {
        this.theoryScore = theoryScore;
    }

    public float getPracticalScore() {
        return practicalScore;
    }

    public void setPracticalScore(float practicalScore) {
        this.practicalScore = practicalScore;
    }

    public float getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(float averageScore) {
        this.averageScore = averageScore;
    }
}
