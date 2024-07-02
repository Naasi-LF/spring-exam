package org.naasi.springexam.controller;

import org.naasi.springexam.pojo.*;
import org.naasi.springexam.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exams")
public class ExamController {

    @Autowired
    private ExamService examService;

    // 获取指定考试的所有题目
    @GetMapping("/{examId}/questions")
    public ResponseEntity<List<Question>> getExamQuestions(@PathVariable int examId) {
        List<Question> questions = examService.findQuestionsByExamId(examId);
        if (questions.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(questions);
    }

    // 学生提交考试答案
    @PostMapping("/{examId}/answers")
    public ResponseEntity<?> submitAnswers(@PathVariable int examId, @RequestBody List<StudentAnswer> answers) {
        boolean result = examService.saveStudentAnswers(answers);
        if (result) {
            return ResponseEntity.ok("Answers submitted successfully.");
        } else {
            return ResponseEntity.badRequest().body("Failed to submit answers.");
        }
    }

    @GetMapping("/{examId}/is-submitted/{studentId}")
    public ResponseEntity<Boolean> isExamSubmitted(@PathVariable int examId, @PathVariable int studentId) {
        boolean isSubmitted = examService.isExamSubmittedByStudent(examId, studentId);
        return ResponseEntity.ok(isSubmitted);
    }

    @PostMapping("/{examId}/grade")
    public ResponseEntity<?> gradeExam(@PathVariable int examId) {
        boolean success = examService.calculateAndInsertScores(examId);
        if (success) {
            return ResponseEntity.ok("Grading completed successfully.");
        } else {
            return ResponseEntity.internalServerError().body("Failed to grade exam.");
        }
    }

    // Endpoint to fetch all results for a specific exam
    @GetMapping("/{examId}/results")
    public ResponseEntity<List<StudentExamResult>> getExamResults(@PathVariable int examId) {
        List<StudentExamResult> results = examService.getExamResults(examId);
        if (results.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(results);
    }
    @GetMapping("/{examId}/statistics")
    public ResponseEntity<ExamStatistics> getExamStatistics(@PathVariable int examId) {
        ExamStatistics stats = examService.getExamStatistics(examId);

        if (stats == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(stats);
    }
}
