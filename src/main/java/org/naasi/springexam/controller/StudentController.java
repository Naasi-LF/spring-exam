package org.naasi.springexam.controller;

import org.naasi.springexam.mapper.StudentInfoMapper;
import org.naasi.springexam.pojo.ExamInfo;
import org.naasi.springexam.pojo.Question;
import org.naasi.springexam.pojo.StudentAnswer;
import org.naasi.springexam.pojo.StudentInfo;
import org.naasi.springexam.security.JwtUtil;
import org.naasi.springexam.service.StudentService;
import org.naasi.springexam.service.ExamService;
import org.naasi.springexam.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private ExamService examService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private StudentInfoMapper studentInfoMapper;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> registerStudent(@RequestBody StudentInfo studentInfo) {
        boolean isRegistered = studentService.register(studentInfo);
        if (isRegistered) {
            String token = jwtUtil.generateToken(studentInfo.getName());
            return ResponseEntity.ok("Student registered successfully. Token: " + token);
        } else {
            return ResponseEntity.badRequest().body("Failed to register student");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam int studentId, @RequestParam String password) {
        if (studentId == 2024 && "qfg".equals(password)) {
            String token = jwtUtil.generateToken("admin");
            return ResponseEntity.ok(Map.of("message", "Teacher login successful", "token", token));
        } else {
            StudentInfo studentInfo = studentInfoMapper.selectById(studentId);
            if (studentInfo != null && studentInfo.getPassword().equals(password)) {
                String token = jwtUtil.generateToken(studentInfo.getName());
                return ResponseEntity.ok(Map.of("message", "Student login successful", "token", token));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
            }
        }
    }

    @PutMapping("/update/{studentId}")
    public ResponseEntity<?> updateStudent(@PathVariable int studentId, @RequestBody StudentInfo studentInfo) {
        studentInfo.setStudentId(studentId);
        boolean isUpdated = studentService.updateStudentInfo(studentInfo);
        if (isUpdated) {
            return ResponseEntity.ok("Student info updated successfully");
        } else {
            return ResponseEntity.badRequest().body("Failed to update student info");
        }
    }

    @DeleteMapping("/delete/{studentId}")
    public ResponseEntity<?> deleteStudent(@RequestHeader("Authorization") String token, @PathVariable("studentId") String studentIdStr) {
        try {
            int studentId = Integer.parseInt(studentIdStr);
            boolean isDeleted = studentService.deleteStudent(studentId);
            if (isDeleted) {
                return ResponseEntity.ok("Student deleted successfully");
            } else {
                return ResponseEntity.badRequest().body("Failed to delete student");
            }
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body("Invalid student ID");
        }
    }

    @GetMapping("/info/{studentId}")
    public ResponseEntity<StudentInfo> getStudentInfo(@PathVariable int studentId) {
        StudentInfo studentInfo = studentService.getStudentById(studentId);
        if (studentInfo != null) {
            return ResponseEntity.ok(studentInfo);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
//    // 显示满足时间的考试
//    @GetMapping("/available-exams")
//    public ResponseEntity<List<ExamInfo>> getAvailableExams() {
//        LocalDateTime now = LocalDateTime.now();
//        System.out.println(now);
//        List<ExamInfo> exams = examService.findExamsAvailable(now);
//        System.out.println(exams);
//        return ResponseEntity.ok(exams);
//    }
//    // 显示考试题目
//    @GetMapping("/exam/{examId}/questions")
//    public ResponseEntity<List<Question>> getExamQuestions(@PathVariable int examId) {
//        List<Question> questions = questionService.findQuestionsByExamId(examId);
//        if (questions.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//        return ResponseEntity.ok(questions);
//    }
//    // 批量上传题目
//    @PostMapping("/submit-answers")
//    public ResponseEntity<?> submitAnswers(@RequestBody List<StudentAnswer> answers) {
//        boolean result = examService.saveStudentAnswers(answers);
//        if (result) {
//            return ResponseEntity.ok("Answers submitted successfully");
//        } else {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to submit answers");
//        }
//    }
}
