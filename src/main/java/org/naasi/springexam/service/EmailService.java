package org.naasi.springexam.service;

import org.naasi.springexam.pojo.ExamStatistics;

import java.util.List;

public interface EmailService {

    boolean send(String to, String title, String content);
    boolean sendBulk(List<String> toList, String title, List<String> contents);

}

