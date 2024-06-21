package org.naasi.springexam.mapper;

import org.apache.ibatis.annotations.*;
import org.naasi.springexam.pojo.StudentInfo;
@Mapper  // 确保每个Mapper接口都有这个注解，如果没有全局的 @MapperScan
public interface StudentInfoMapper {
    // 插入学生信息，相当于注册
    @Insert("INSERT INTO student_info(student_id, password, class, name) VALUES(#{studentId}, #{password}, #{className}, #{name})")
    int insert(StudentInfo studentInfo);
    // 通过id寻找学生
    @Select("SELECT * FROM student_info WHERE student_id = #{studentId}")
    StudentInfo selectById(int studentId);
    // 修改学生相关信息
    @Update("UPDATE student_info SET password = #{password}, class = #{className}, name = #{name} WHERE student_id = #{studentId}")
    int update(StudentInfo studentInfo);
    // 注销
    @Delete("DELETE FROM student_info WHERE student_id = #{studentId}")
    int delete(int studentId);
    // 登录认证
    @Select("SELECT * FROM student_info WHERE student_id = #{studentId} AND password = #{password}")
    StudentInfo login(int studentId, String password);
}
