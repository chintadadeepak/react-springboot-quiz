package com.deepak.quizmicroservice.models;

import lombok.Data;

@Data
public class QuizDto {
    private String category;
    private Integer noOfQuestions;
    private String quizTitle;
}
