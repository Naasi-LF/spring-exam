package org.naasi.springexam.mapper;

import org.apache.ibatis.annotations.*;
import org.naasi.springexam.pojo.Question;
@Mapper  // 确保每个Mapper接口都有这个注解，如果没有全局的 @MapperScan
public interface QuestionMapper {
    // 插入题目信息
    @Insert("INSERT INTO questions(question_id, description, option_a, option_b, option_c, option_d, correct_answer) VALUES(#{questionId}, #{description}, #{optionA}, #{optionB}, #{optionC}, #{optionD}, #{correctAnswer})")
    int insert(Question question);
    // 通过id查找某个题目
    @Select("SELECT * FROM questions WHERE question_id = #{questionId}")
    Question selectById(int questionId);
    // 更新题目信息
    @Update("UPDATE questions SET description = #{description}, option_a = #{optionA}, option_b = #{optionB}, option_c = #{optionC}, option_d = #{optionD}, correct_answer = #{correctAnswer} WHERE question_id = #{questionId}")
    int update(Question question);
    // 删除题目
    @Delete("DELETE FROM questions WHERE question_id = #{questionId}")
    int delete(int questionId);
}
