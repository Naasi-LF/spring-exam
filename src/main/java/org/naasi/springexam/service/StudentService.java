package org.naasi.springexam.service;

import org.naasi.springexam.pojo.StudentInfo;

public interface StudentService {
    boolean register(StudentInfo studentInfo);
    StudentInfo login(int studentId, String password);
    boolean updateStudentInfo(StudentInfo studentInfo);
    boolean deleteStudent(int studentId);
    StudentInfo getStudentById(int studentId);  // 新增方法
}
