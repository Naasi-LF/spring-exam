package org.naasi.springexam.service;

import org.naasi.springexam.pojo.ExamInfo;
import java.util.List;

public interface ExamService {
    int insertExam(ExamInfo examInfo);
    void linkExamWithQuestion(int examId, List<Integer> questionIds);
}
