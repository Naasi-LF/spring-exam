package org.naasi.springexam.mapper;

import org.apache.ibatis.annotations.*;
import org.naasi.springexam.pojo.ExamInfo;
import org.naasi.springexam.pojo.Question;

import java.time.LocalDateTime;
import java.util.List;
@Mapper  // 确保每个Mapper接口都有这个注解，如果没有全局的 @MapperScan
public interface ExamInfoMapper {
    // 插入考试信息
    // 插入考试信息
    @Insert("INSERT INTO exam_info(exam_name, start_time, end_time) VALUES(#{examName}, #{startTime}, #{endTime})")
    @Options(useGeneratedKeys = true, keyProperty = "examId", keyColumn = "exam_id")
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

    @Select("SELECT * FROM exam_info WHERE end_time >= NOW()")
    @Results({
            @Result(property = "examId", column = "exam_id"),
            @Result(property = "examName", column = "exam_name"),
            @Result(property = "startTime", column = "start_time"),
            @Result(property = "endTime", column = "end_time")
    })
    List<ExamInfo> findActiveExams();
}

