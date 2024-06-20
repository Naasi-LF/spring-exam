package org.naasi.springexam.mapper;

import org.apache.ibatis.annotations.*;
import org.naasi.springexam.pojo.ExamQuestion;

public interface ExamQuestionMapper {
    @Insert("INSERT INTO exam_questions(exam_id, question_id) VALUES(#{examId}, #{questionId})")
    int insert(ExamQuestion examQuestion);

    @Select("SELECT * FROM exam_questions WHERE exam_id = #{examId} AND question_id = #{questionId}")
    ExamQuestion selectById(int examId, int questionId);

    @Delete("DELETE FROM exam_questions WHERE exam_id = #{examId} AND question_id = #{questionId}")
    int delete(int examId, int questionId);
}
