package org.naasi.springexam.service;

import org.naasi.springexam.pojo.*;

import java.time.LocalDateTime;
import java.util.List;

public interface ExamService {
    int insertExam(ExamInfo examInfo);
    void linkExamWithQuestion(int examId, List<Integer> questionIds);
    //List<ExamInfo> findExamsAvailable(LocalDateTime now);  // 查找当前可参加的考试
    List<Question> findQuestionsByExamId(int examId);
    boolean saveStudentAnswers(List<StudentAnswer> answers);
    boolean isExamSubmittedByStudent(int examId, int studentId);

    boolean calculateAndInsertScores(int examId);  // 计算并插入成绩的方法
    List<StudentExamResult> getExamResults(int examId);

}
