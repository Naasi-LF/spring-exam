package org.naasi.springexam.mapper;

import org.apache.ibatis.annotations.*;
import org.naasi.springexam.pojo.ExamResult;
@Mapper  // 确保每个Mapper接口都有这个注解，如果没有全局的 @MapperScan
public interface ExamResultMapper {
    // 添加学生考试结果的第一个函数
    @Insert("INSERT INTO exam_results(student_id, exam_id, score) VALUES(#{studentId}, #{examId}, #{score})")
    int insert(ExamResult examResult);

    @Select("SELECT * FROM exam_results WHERE student_id = #{studentId} AND exam_id = #{examId}")
    ExamResult selectById(int studentId, int examId);

    @Update("UPDATE exam_results SET score = #{score} WHERE student_id = #{studentId} AND exam_id = #{examId}")
    int update(ExamResult examResult);

    @Delete("DELETE FROM exam_results WHERE student_id = #{studentId} AND exam_id = #{examId}")
    int delete(int studentId, int examId);

    // 计算考试成绩，先要通过此方法后才可以插入学生成绩
    @Select("SELECT sum(case when is_correct = true then 1 else 0 end) * (100/count(*)) as score FROM student_answers WHERE exam_id = #{examId} AND student_id = #{studentId}")
    double calculateScore(int studentId, int examId);

    // 第二种插入学生成绩的方法
    @Insert("INSERT INTO exam_results(student_id, exam_id, score) VALUES(#{studentId}, #{examId}, #{score})")
    int insertExamResult(int studentId, int examId, double score);
}