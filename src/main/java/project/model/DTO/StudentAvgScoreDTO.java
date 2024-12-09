package project.model.DTO;

import java.math.BigDecimal;

public class StudentAvgScoreDTO {
    private int classId;
    private String className;
    private String tutorName;
    private String subjectName;
    private int studentNumber;
    private BigDecimal avgScore;

    public StudentAvgScoreDTO() {}

    public StudentAvgScoreDTO(int classId, String className, String tutorName, String subjectName, int studentNumber, BigDecimal avgScore) {
        this.classId = classId;
        this.className = className;
        this.tutorName = tutorName;
        this.subjectName = subjectName;
        this.studentNumber = studentNumber;
        this.avgScore = avgScore;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getTutorName() {
        return tutorName;
    }

    public void setTutorName(String tutorName) {
        this.tutorName = tutorName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(int studentNumber) {
        this.studentNumber = studentNumber;
    }

    public BigDecimal getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(BigDecimal avgScore) {
        this.avgScore = avgScore;
    }
}
