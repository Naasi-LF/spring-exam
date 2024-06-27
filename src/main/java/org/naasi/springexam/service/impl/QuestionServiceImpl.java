package org.naasi.springexam.service.impl;

import org.naasi.springexam.mapper.QuestionMapper;
import org.naasi.springexam.pojo.Question;
import org.naasi.springexam.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Override
    public boolean addQuestion(Question question) {
        return questionMapper.insert(question) == 1;
    }

    @Override
    public List<Question> findAll() {
        return questionMapper.selectAll();
    }

    @Override
    public List<Question> findQuestionsByExamId(int examId) {
        return questionMapper.findQuestionsByExamId(examId);
    }
}
