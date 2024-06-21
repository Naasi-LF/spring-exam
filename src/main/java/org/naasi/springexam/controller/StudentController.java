package org.naasi.springexam.controller;

import org.naasi.springexam.mapper.StudentInfoMapper;
import org.naasi.springexam.pojo.StudentInfo;
import org.naasi.springexam.security.JwtUtil;
import org.naasi.springexam.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;
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



    @PutMapping("/update")
    public ResponseEntity<?> updateStudent(@RequestHeader("Authorization") String token, @RequestBody StudentInfo studentInfo) {
        if (!jwtUtil.validateToken(token, studentInfo.getName())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }
        boolean isUpdated = studentService.updateStudentInfo(studentInfo);
        if (isUpdated) {
            return ResponseEntity.ok("Student info updated successfully");
        } else {
            return ResponseEntity.badRequest().body("Failed to update student info");
        }
    }

    @DeleteMapping("/delete/{studentId}")
    public ResponseEntity<?> deleteStudent(@RequestHeader("Authorization") String token, @PathVariable int studentId) {
        if (!jwtUtil.validateToken(token, String.valueOf(studentId))) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }
        boolean isDeleted = studentService.deleteStudent(studentId);
        if (isDeleted) {
            return ResponseEntity.ok("Student deleted successfully");
        } else {
            return ResponseEntity.badRequest().body("Failed to delete student");
        }
    }
}
