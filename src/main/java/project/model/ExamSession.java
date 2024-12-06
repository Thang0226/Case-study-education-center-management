package project.model;

import java.time.LocalDate;

public class ExamSession {
    private String id;
    private String name;
    private LocalDate examDate;

    public ExamSession() {}

    public ExamSession(String id, String name, LocalDate examDate) {
        this.id = id;
        this.name = name;
        this.examDate = examDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getExamDate() {
        return examDate;
    }

    public void setExamDate(LocalDate examDate) {
        this.examDate = examDate;
    }
}
