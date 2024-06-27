package org.naasi.springexam.controller;

import org.naasi.springexam.mapper.ExamInfoMapper;
import org.naasi.springexam.pojo.ExamInfo;
import org.naasi.springexam.pojo.ExamPublishDTO;
import org.naasi.springexam.pojo.Question;
import org.naasi.springexam.service.ExamService;
import org.naasi.springexam.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private ExamService examService;  // 注入ExamService

    @Autowired
    private ExamInfoMapper examInfoMapper;
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


}
