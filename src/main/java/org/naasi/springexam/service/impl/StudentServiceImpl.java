package org.naasi.springexam.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.naasi.springexam.mapper.StudentInfoMapper;
import org.naasi.springexam.pojo.StudentInfo;
import org.naasi.springexam.service.StudentService;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentInfoMapper studentInfoMapper;

    @Override
    @Transactional
    public boolean register(StudentInfo studentInfo) {
        return studentInfoMapper.insert(studentInfo) == 1;
    }

    @Override
    public StudentInfo login(int studentId, String password) {
        return studentInfoMapper.login(studentId, password);
    }

    @Override
    @Transactional
    public boolean updateStudentInfo(StudentInfo studentInfo) {
        return studentInfoMapper.update(studentInfo) == 1;
    }


    @Override
    public List<StudentInfo> findAllStudents() {
        return studentInfoMapper.findAllStudents();
    }

    @Override
    @Transactional
    public boolean deleteStudent(int studentId) {
        return studentInfoMapper.delete(studentId) == 1;
    }

    @Override
    public StudentInfo getStudentById(int studentId) {
        return studentInfoMapper.selectById(studentId);
    }
}
