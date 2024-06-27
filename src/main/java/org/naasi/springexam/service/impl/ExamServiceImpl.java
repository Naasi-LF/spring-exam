package org.naasi.springexam.service.impl;

import org.naasi.springexam.mapper.ExamInfoMapper;
import org.naasi.springexam.mapper.ExamQuestionMapper;
import org.naasi.springexam.mapper.StudentAnswerMapper;
import org.naasi.springexam.pojo.ExamInfo;
import org.naasi.springexam.pojo.ExamQuestion;
import org.naasi.springexam.pojo.StudentAnswer;
import org.naasi.springexam.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ExamServiceImpl implements ExamService {
    @Autowired
    private ExamInfoMapper examInfoMapper;
    @Autowired
    private ExamQuestionMapper examQuestionMapper;
    @Autowired
    private StudentAnswerMapper studentAnswerMapper;

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
    @Transactional
    public boolean saveStudentAnswers(List<StudentAnswer> answers) {
        // 假设已有批量插入方法
        return studentAnswerMapper.batchInsert(answers) == answers.size();
    }
}
