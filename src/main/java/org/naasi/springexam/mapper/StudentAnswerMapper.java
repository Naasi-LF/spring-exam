package org.naasi.springexam.mapper;

import org.apache.ibatis.annotations.*;
import org.naasi.springexam.pojo.StudentAnswer;
@Mapper  // 确保每个Mapper接口都有这个注解，如果没有全局的 @MapperScan
public interface StudentAnswerMapper {
    // 学生提交一个题目，会有一个题目的学生题目答案数据
    @Insert("INSERT INTO student_answers(student_id, exam_id, question_id, correct_answer, student_answer, is_correct) VALUES(#{studentId}, #{examId}, #{questionId}, #{correctAnswer}, #{studentAnswer}, #{isCorrect})")
    int insert(StudentAnswer studentAnswer);

    @Select("SELECT * FROM student_answers WHERE student_id = #{studentId} AND exam_id = #{examId} AND question_id = #{questionId}")
    StudentAnswer selectById(int studentId, int examId, int questionId);

    @Update("UPDATE student_answers SET correct_answer = #{correctAnswer}, student_answer = #{studentAnswer}, is_correct = #{isCorrect} WHERE student_id = #{studentId} AND exam_id = #{examId} AND question_id = #{questionId}")
    int update(StudentAnswer studentAnswer);

    @Delete("DELETE FROM student_answers WHERE student_id = #{studentId} AND exam_id = #{examId} AND question_id = #{questionId}")
    int delete(int studentId, int examId, int questionId);

    // 插入学生答案并自动计算正确性
    @Insert("INSERT INTO student_answers(student_id, exam_id, question_id, correct_answer, student_answer, is_correct) " +
            "VALUES(#{studentId}, #{examId}, #{questionId}, " +
            "(SELECT correct_answer FROM questions WHERE question_id = #{questionId}), " +
            "#{studentAnswer}, " +
            "(SELECT correct_answer FROM questions WHERE question_id = #{questionId}) = #{studentAnswer})")
    int insertWithAutoCheck(StudentAnswer studentAnswer);
}