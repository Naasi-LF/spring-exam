package org.naasi.springexam.service.impl;

import org.naasi.springexam.mapper.*;
import org.naasi.springexam.pojo.*;
import org.naasi.springexam.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class ExamServiceImpl implements ExamService {
    @Autowired
    private ExamInfoMapper examInfoMapper;
    @Autowired
    private ExamQuestionMapper examQuestionMapper;
    @Autowired
    private StudentAnswerMapper studentAnswerMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private ExamResultMapper examResultMapper;
    @Override
    @Transactional
    public int insertExam(ExamInfo examInfo) {
        examInfoMapper.insert(examInfo);
        return examInfo.getExamId();
    }

    @Override
    @Transactional
    public void linkExamWithQuestion(int examId, List<Integer> questionIds) {
        questionIds.forEach(questionId -> {
            examQuestionMapper.insert(new ExamQuestion(examId, questionId));
        });
    }

    @Override
    public List<Question> findQuestionsByExamId(int examId) {
        return questionMapper.findQuestionsByExamId(examId);
    }
    @Override
    @Transactional
    public boolean saveStudentAnswers(List<StudentAnswer> answers) {
        return studentAnswerMapper.insertStudentAnswers(answers) == answers.size();
    }
    @Override
    public boolean isExamSubmittedByStudent(int examId, int studentId) {
        return studentAnswerMapper.existsByStudentIdAndExamId(studentId, examId);
    }

    @Override
    public boolean calculateAndInsertScores(int examId) {
        try {
            examResultMapper.calculateAndInsertScores(examId);
            return true;
        } catch (Exception e) {
            // Log the exception details
            System.err.println("Error in calculating and inserting scores: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<StudentExamResult> getExamResults(int examId) {
        return examResultMapper.findAllResultsByExamId(examId);
    }

    @Override
    public ExamStatistics getExamStatistics(int examId) {
        List<StudentExamResult> results = examResultMapper.findAllResultsByExamId(examId);
        if (results.isEmpty()) {
            return null;
        }

        double sum = 0;
        double max = Double.MIN_VALUE;
        double min = Double.MAX_VALUE;
        for (StudentExamResult result : results) {
            double score = result.getScore();
            sum += score;
            if (score > max) {
                max = score;
            }
            if (score < min) {
                min = score;
            }
        }

        double average = sum / results.size();

        return new ExamStatistics(average, max, min, results);
    }
}
