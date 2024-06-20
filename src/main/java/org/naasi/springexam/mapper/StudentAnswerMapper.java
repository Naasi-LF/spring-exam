package org.naasi.springexam.mapper;

import org.apache.ibatis.annotations.*;
import org.naasi.springexam.pojo.StudentAnswer;

public interface StudentAnswerMapper {
    @Insert("INSERT INTO student_answers(student_id, exam_id, question_id, correct_answer, student_answer, is_correct) VALUES(#{studentId}, #{examId}, #{questionId}, #{correctAnswer}, #{studentAnswer}, #{isCorrect})")
    int insert(StudentAnswer studentAnswer);

    @Select("SELECT * FROM student_answers WHERE student_id = #{studentId} AND exam_id = #{examId} AND question_id = #{questionId}")
    StudentAnswer selectById(int studentId, int examId, int questionId);

    @Update("UPDATE student_answers SET correct_answer = #{correctAnswer}, student_answer = #{studentAnswer}, is_correct = #{isCorrect} WHERE student_id = #{studentId} AND exam_id = #{examId} AND question_id = #{questionId}")
    int update(StudentAnswer studentAnswer);

    @Delete("DELETE FROM student_answers WHERE student_id = #{studentId} AND exam_id = #{examId} AND question_id = #{questionId}")
    int delete(int studentId, int examId, int questionId);
}