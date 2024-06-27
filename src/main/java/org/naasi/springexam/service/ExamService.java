package org.naasi.springexam.service;

import org.naasi.springexam.pojo.ExamInfo;
import org.naasi.springexam.pojo.StudentAnswer;

import java.time.LocalDateTime;
import java.util.List;

public interface ExamService {
    int insertExam(ExamInfo examInfo);
    void linkExamWithQuestion(int examId, List<Integer> questionIds);
//    List<ExamInfo> findExamsAvailable(LocalDateTime now);  // 查找当前可参加的考试
    boolean saveStudentAnswers(List<StudentAnswer> answers);  // 保存学生答案的方法
}
