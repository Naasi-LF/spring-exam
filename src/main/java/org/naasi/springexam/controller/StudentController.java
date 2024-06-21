package org.naasi.springexam.controller;

import org.naasi.springexam.mapper.QuestionMapper;
import org.naasi.springexam.mapper.StudentInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.naasi.springexam.pojo.StudentInfo;
import org.naasi.springexam.service.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentInfoMapper studentInfoMapper;
    @PostMapping("/register")
    public ResponseEntity<?> registerStudent(@RequestBody StudentInfo studentInfo) {
        boolean isRegistered = studentService.register(studentInfo);
        if (isRegistered) {
            return ResponseEntity.ok("Student registered successfully");
        } else {
            return ResponseEntity.badRequest().body("Failed to register student");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam int studentId, @RequestParam String password) {
        // 检查是否是教师账户
        if (studentId == 123456 && "admin".equals(password)) {
            return ResponseEntity.ok("Teacher login successful");
        } else {
            // 从数据库中查询学生信息
            StudentInfo studentInfo = studentInfoMapper.selectById(studentId);
            if (studentInfo != null && studentInfo.getPassword().equals(password)) {
                return ResponseEntity.ok("Student login successful");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
            }
        }
    }
    @PutMapping("/update")
    public ResponseEntity<?> updateStudent(@RequestBody StudentInfo studentInfo) {
        boolean isUpdated = studentService.updateStudentInfo(studentInfo);
        if (isUpdated) {
            return ResponseEntity.ok("Student info updated successfully");
        } else {
            return ResponseEntity.badRequest().body("Failed to update student info");
        }
    }

    @DeleteMapping("/delete/{studentId}")
    public ResponseEntity<?> deleteStudent(@PathVariable int studentId) {
        boolean isDeleted = studentService.deleteStudent(studentId);
        if (isDeleted) {
            return ResponseEntity.ok("Student deleted successfully");
        } else {
            return ResponseEntity.badRequest().body("Failed to delete student");
        }
    }
}
