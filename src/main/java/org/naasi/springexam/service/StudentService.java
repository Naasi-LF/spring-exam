package org.naasi.springexam.service;

import org.naasi.springexam.pojo.StudentInfo;

import java.util.List;

public interface StudentService {
    boolean register(StudentInfo studentInfo);
    StudentInfo login(int studentId, String password);
    boolean updateStudentInfo(StudentInfo studentInfo);
    List<StudentInfo> findAllStudents();
    boolean deleteStudent(int studentId);
    StudentInfo getStudentById(int studentId);  // 新增方法
}
