package org.naasi.springexam.pojo;

public class ExamQuestion {
    private int examId;
    private int questionId;
    // 默认构造函数
    public ExamQuestion() {
    }

    // 添加一个新的构造函数
    public ExamQuestion(int examId, int questionId) {
        this.examId = examId;
        this.questionId = questionId;
    }
    // Getters and Setters
    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }
}