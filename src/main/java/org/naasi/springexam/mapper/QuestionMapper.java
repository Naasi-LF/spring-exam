package org.naasi.springexam.mapper;

import org.apache.ibatis.annotations.*;
import org.naasi.springexam.pojo.Question;

public interface QuestionMapper {
    @Insert("INSERT INTO questions(question_id, description, option_a, option_b, option_c, option_d, correct_answer) VALUES(#{questionId}, #{description}, #{optionA}, #{optionB}, #{optionC}, #{optionD}, #{correctAnswer})")
    int insert(Question question);

    @Select("SELECT * FROM questions WHERE question_id = #{questionId}")
    Question selectById(int questionId);

    @Update("UPDATE questions SET description = #{description}, option_a = #{optionA}, option_b = #{optionB}, option_c = #{optionC}, option_d = #{optionD}, correct_answer = #{correctAnswer} WHERE question_id = #{questionId}")
    int update(Question question);

    @Delete("DELETE FROM questions WHERE question_id = #{questionId}")
    int delete(int questionId);
}
