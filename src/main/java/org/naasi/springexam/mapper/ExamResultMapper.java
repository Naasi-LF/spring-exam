package org.naasi.springexam.mapper;

import org.apache.ibatis.annotations.*;
import org.naasi.springexam.pojo.ExamResult;

public interface ExamResultMapper {
    @Insert("INSERT INTO exam_results(student_id, exam_id, score) VALUES(#{studentId}, #{examId}, #{score})")
    int insert(ExamResult examResult);

    @Select("SELECT * FROM exam_results WHERE student_id = #{studentId} AND exam_id = #{examId}")
    ExamResult selectById(int studentId, int examId);

    @Update("UPDATE exam_results SET score = #{score} WHERE student_id = #{studentId} AND exam_id = #{examId}")
    int update(ExamResult examResult);

    @Delete("DELETE FROM exam_results WHERE student_id = #{studentId} AND exam_id = #{examId}")
    int delete(int studentId, int examId);
}