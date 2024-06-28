package org.naasi.springexam.controller;

import org.naasi.springexam.mapper.ExamInfoMapper;
import org.naasi.springexam.pojo.*;
import org.naasi.springexam.service.EmailService;
import org.naasi.springexam.service.ExamService;
import org.naasi.springexam.service.QuestionService;
import org.naasi.springexam.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private QuestionService questionService;

    @Autowired
    private ExamService examService;  // 注入ExamService

    @Autowired
    private ExamInfoMapper examInfoMapper;

    @Autowired
    private EmailService emailService;

    @PostMapping("/addQuestion")
    public ResponseEntity<?> addQuestion(@RequestBody Question question) {
        boolean isAdded = questionService.addQuestion(question);
        if (isAdded) {
            return ResponseEntity.ok("Question added successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to add question");
        }
    }

    @PostMapping("/publishExam")
    public ResponseEntity<?> publishExam(@RequestBody ExamPublishDTO examPublishDTO) {
        int examId = examService.insertExam(examPublishDTO.getExamInfo());
        if (examId > 0) {
            examService.linkExamWithQuestion(examId, examPublishDTO.getQuestionIds());
            return ResponseEntity.ok("Exam published successfully with ID: " + examId);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to publish exam");
        }
    }

    // 获取所有题目
    @GetMapping("/questions")
    public ResponseEntity<List<Question>> getAllQuestions() {
        List<Question> questions = questionService.findAll();
        return ResponseEntity.ok(questions);
    }
    @GetMapping("/activeExams")
    public ResponseEntity<List<ExamInfo>> getActiveExams() {
        List<ExamInfo> activeExams = examInfoMapper.findActiveExams();
        if (activeExams.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(activeExams);
    }

    // 获取所有学生信息
    @GetMapping("/students")
    public ResponseEntity<List<StudentInfo>> getAllStudents() {
        List<StudentInfo> students = studentService.findAllStudents();
        if (students.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(students);
    }
    @Autowired
    private EmailProperties emailProperties;
    @GetMapping("/sendExamResults/{examId}")
    public ResponseEntity<?> sendExamResults(@PathVariable int examId) {
        System.out.println(emailProperties);
        List<StudentExamResult> results = examService.getExamResults(examId);
        List<String> emails = new ArrayList<>();
        List<String> contents = new ArrayList<>();

        for (StudentExamResult result : results) {
            emails.add(result.getStudentId() + "@czu.cn");  // 构建邮箱地址
            contents.add("您的成绩是：" + result.getScore());  // 构建邮件内容
        }

        // 使用sendBulk方法批量发送邮件
        boolean allEmailsSent = emailService.sendBulk(emails, "考试成绩通知", contents);
        return allEmailsSent ? ResponseEntity.ok("所有成绩邮件已发送") : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("发送邮件过程中有失败的情况");
    }
}
