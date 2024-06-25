package org.naasi.springexam.service.impl;

import org.naasi.springexam.mapper.ExamInfoMapper;
import org.naasi.springexam.mapper.ExamQuestionMapper;
import org.naasi.springexam.pojo.ExamInfo;
import org.naasi.springexam.pojo.ExamQuestion;
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

    @Override
    @Transactional
    public int insertExam(ExamInfo examInfo) {
        examInfoMapper.insert(examInfo);
        return examInfo.getExamId(); // This assumes that the ID is set on the examInfo object after insertion
    }

    @Override
    @Transactional
    public void linkExamWithQuestion(int examId, List<Integer> questionIds) {
        questionIds.forEach(questionId -> {
            examQuestionMapper.insert(new ExamQuestion(examId, questionId));
        });
    }
}
