package org.naasi.springexam.pojo;

import java.util.List;

public class ExamPublishDTO {
    private ExamInfo examInfo;
    private List<Integer> questionIds;

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
