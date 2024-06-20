package org.naasi.springexam.mapper;

import org.apache.ibatis.annotations.*;
import org.naasi.springexam.pojo.StudentInfo;

public interface StudentInfoMapper {
    @Insert("INSERT INTO student_info(student_id, password, class, name) VALUES(#{studentId}, #{password}, #{className}, #{name})")
    int insert(StudentInfo studentInfo);

    @Select("SELECT * FROM student_info WHERE student_id = #{studentId}")
    StudentInfo selectById(int studentId);

    @Update("UPDATE student_info SET password = #{password}, class = #{className}, name = #{name} WHERE student_id = #{studentId}")
    int update(StudentInfo studentInfo);

    @Delete("DELETE FROM student_info WHERE student_id = #{studentId}")
    int delete(int studentId);
}
