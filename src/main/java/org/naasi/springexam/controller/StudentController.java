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

    @PutMapping("/update/{studentId}")
    public ResponseEntity<?> updateStudent(@PathVariable int studentId, @RequestBody StudentInfo studentInfo) {
        // 设置 studentInfo 的学号
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
            // Temporarily bypass JWT validation to test the rest of the functionality
            // if (!jwtUtil.validateToken(token, String.valueOf(studentId))) {
            //     return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
            // }
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
        StudentInfo studentInfo = studentInfoMapper.selectById(studentId);
        if (studentInfo != null) {
            return ResponseEntity.ok(studentInfo);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

}
