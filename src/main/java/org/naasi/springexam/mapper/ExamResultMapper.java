package org.naasi.springexam.mapper;

import org.apache.ibatis.annotations.*;
import org.naasi.springexam.pojo.ExamResult;
import org.naasi.springexam.pojo.StudentExamResult;

import java.util.List;

@Mapper  // 确保每个Mapper接口都有这个注解，如果没有全局的 @MapperScan
public interface ExamResultMapper {
//    // 添加学生考试结果的第一个函数
//    @Insert("INSERT INTO exam_results(student_id, exam_id, score) VALUES(#{studentId}, #{examId}, #{score})")
//    int insert(ExamResult examResult);
//
//    @Select("SELECT * FROM exam_results WHERE student_id = #{studentId} AND exam_id = #{examId}")
//    ExamResult selectById(int studentId, int examId);
//
//    @Update("UPDATE exam_results SET score = #{score} WHERE student_id = #{studentId} AND exam_id = #{examId}")
//    int update(ExamResult examResult);
//
//    @Delete("DELETE FROM exam_results WHERE student_id = #{studentId} AND exam_id = #{examId}")
//    int delete(int studentId, int examId);
//
//    // 计算考试成绩，先要通过此方法后才可以插入学生成绩
//    @Select("SELECT sum(case when is_correct = true then 1 else 0 end) * (100/count(*)) as score FROM student_answers WHERE exam_id = #{examId} AND student_id = #{studentId}")
//    double calculateScore(int studentId, int examId);

//    // 第二种插入学生成绩的方法
//    @Insert("INSERT INTO exam_results(student_id, exam_id, score) VALUES(#{studentId}, #{examId}, #{score})")
//    int insertExamResult(int studentId, int examId, double score);
//
    // 插入或更新考试成绩
    @Insert({
            "<script>",
            "INSERT INTO exam_results (student_id, exam_id, score) ",
            "SELECT student_id, exam_id, ",
            "(COUNT(case when is_correct = true then 1 end) * 100.0 / COUNT(*)) as score ",
            "FROM student_answers ",
            "WHERE exam_id = #{examId} ",
            "GROUP BY student_id, exam_id ",
            "ON DUPLICATE KEY UPDATE ",
            "score = VALUES(score)",
            "</script>"
    })
    int calculateAndInsertScores(int examId);


    // 获取指定考试的所有学生成绩
    @Select("SELECT si.student_id, si.name, si.class, er.score " +
            "FROM exam_results er " +
            "JOIN student_info si ON er.student_id = si.student_id " +
            "WHERE er.exam_id = #{examId}")
    @Results({
            @Result(property = "studentId", column = "student_id"),
            @Result(property = "name", column = "name"),
            @Result(property = "className", column = "class"),
            @Result(property = "score", column = "score")
    })
    List<StudentExamResult> findAllResultsByExamId(int examId);
}