package org.naasi.springexam.mapper;

import org.apache.ibatis.annotations.*;
import org.naasi.springexam.pojo.ExamInfo;

public interface ExamInfoMapper {
    @Insert("INSERT INTO exam_info(exam_id, exam_name, start_time, end_time) VALUES(#{examId}, #{examName}, #{startTime}, #{endTime})")
    int insert(ExamInfo examInfo);

    @Select("SELECT * FROM exam_info WHERE exam_id = #{examId}")
    ExamInfo selectById(int examId);

    @Update("UPDATE exam_info SET exam_name = #{examName}, start_time = #{startTime}, end_time = #{endTime} WHERE exam_id = #{examId}")
    int update(ExamInfo examInfo);

    @Delete("DELETE FROM exam_info WHERE exam_id = #{examId}")
    int delete(int examId);
}

