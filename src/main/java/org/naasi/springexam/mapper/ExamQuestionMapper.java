package org.naasi.springexam.mapper;

import org.apache.ibatis.annotations.*;
import org.naasi.springexam.pojo.ExamQuestion;
@Mapper  // 确保每个Mapper接口都有这个注解，如果没有全局的 @MapperScan
public interface ExamQuestionMapper {
    // 关联起来考试id和题库中的问题id，这样便可以知道考试的题目
    @Insert("INSERT INTO exam_questions(exam_id, question_id) VALUES(#{examId}, #{questionId})")
    int insert(ExamQuestion examQuestion);

    @Select("SELECT * FROM exam_questions WHERE exam_id = #{examId} AND question_id = #{questionId}")
    ExamQuestion selectById(int examId, int questionId);

    @Delete("DELETE FROM exam_questions WHERE exam_id = #{examId} AND question_id = #{questionId}")
    int delete(int examId, int questionId);

}
