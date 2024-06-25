package org.naasi.springexam.pojo;

import java.util.List;

public class PublishExamRequest {
    private ExamInfo examInfo;
    private List<Integer> questionIds;

    // Getters and setters
    public ExamInfo getExamInfo() {
        return examInfo;
    }

    public void setExamInfo(ExamInfo examInfo) {
        this.examInfo = examInfo;
    }

    public List<Integer> getQuestionIds() {
        return questionIds;
    }

    public void setQuestionIds(List<Integer> questionIds) {
        this.questionIds = questionIds;
    }
}
