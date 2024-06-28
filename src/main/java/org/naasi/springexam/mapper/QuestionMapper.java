package org.naasi.springexam.mapper;
import java.util.List;
import org.apache.ibatis.annotations.*;
import org.naasi.springexam.pojo.Question;
@Mapper
public interface QuestionMapper {
    // 插入题目信息
    @Insert("INSERT INTO questions(description, option_a, option_b, option_c, option_d, correct_answer) VALUES(#{description}, #{optionA}, #{optionB}, #{optionC}, #{optionD}, #{correctAnswer})")
    @Options(useGeneratedKeys = true, keyProperty = "questionId")
    int insert(Question question);

    // 通过id查找某个题目
    @Select("SELECT * FROM questions WHERE question_id = #{questionId}")
    Question selectById(int questionId);

    // 获取所有题目
    // 添加获取所有题目的方法
    @Select("SELECT * FROM questions")
    @Results({
            @Result(property = "questionId", column = "question_id"),
            @Result(property = "description", column = "description"),
            @Result(property = "optionA", column = "option_a"),
            @Result(property = "optionB", column = "option_b"),
            @Result(property = "optionC", column = "option_c"),
            @Result(property = "optionD", column = "option_d"),
            @Result(property = "correctAnswer", column = "correct_answer")
    })
    List<Question> selectAll();    // 更新题目信息
    @Update("UPDATE questions SET description = #{description}, option_a = #{optionA}, option_b = #{optionB}, option_c = #{optionC}, option_d = #{optionD}, correct_answer = #{correctAnswer} WHERE question_id = #{questionId}")
    int update(Question question);

    // 删除题目
    @Delete("DELETE FROM questions WHERE question_id = #{questionId}")
    int delete(int questionId);

    @Select("SELECT * FROM questions WHERE question_id IN (SELECT question_id FROM exam_questions WHERE exam_id = #{examId})")
    @Results({
            @Result(property = "questionId", column = "question_id"),
            @Result(property = "description", column = "description"),
            @Result(property = "optionA", column = "option_a"),
            @Result(property = "optionB", column = "option_b"),
            @Result(property = "optionC", column = "option_c"),
            @Result(property = "optionD", column = "option_d"),
            @Result(property = "correctAnswer", column = "correct_answer")
    })
    List<Question> findQuestionsByExamId(int examId);

}
