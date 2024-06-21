package org.naasi.springexam.mapper;

import org.apache.ibatis.annotations.*;
import org.naasi.springexam.pojo.ExamInfo;
import org.naasi.springexam.pojo.Question;

import java.util.List;
@Mapper  // 确保每个Mapper接口都有这个注解，如果没有全局的 @MapperScan
public interface ExamInfoMapper {
    // 插入考试信息
    @Insert("INSERT INTO exam_info(exam_id, exam_name, start_time, end_time) VALUES(#{examId}, #{examName}, #{startTime}, #{endTime})")
    int insert(ExamInfo examInfo);
    // 查找
    @Select("SELECT * FROM exam_info WHERE exam_id = #{examId}")
    ExamInfo selectById(int examId);
    // 改
    @Update("UPDATE exam_info SET exam_name = #{examName}, start_time = #{startTime}, end_time = #{endTime} WHERE exam_id = #{examId}")
    int update(ExamInfo examInfo);
    // 删
    @Delete("DELETE FROM exam_info WHERE exam_id = #{examId}")
    int delete(int examId);
    // 查询一个考试包含的所有题目
    @Select("SELECT q.* FROM exam_info ei " +
            "JOIN exam_questions eq ON ei.exam_id = eq.exam_id " +
            "JOIN questions q ON eq.question_id = q.question_id " +
            "WHERE ei.exam_id = #{examId}")
    List<Question> findQuestionsByExamId(int examId);
}

