package org.naasi.springexam.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class ExamInfo {
    // Getters and Setters
    private int examId;
    private String examName;
    private Date startTime;
    private Date endTime;

}