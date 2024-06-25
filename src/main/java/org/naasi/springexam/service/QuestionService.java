package org.naasi.springexam.service;

import org.naasi.springexam.pojo.Question;

import java.util.List;

public interface QuestionService {
    boolean addQuestion(Question question);
    List<Question> findAll();  // 添加获取所有题目的方法
}
