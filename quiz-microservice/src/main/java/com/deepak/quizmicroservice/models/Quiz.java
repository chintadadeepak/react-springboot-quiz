package com.deepak.quizmicroservice.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Entity
@Data
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String quizTitle;
    @ElementCollection
    private List<Integer> questionIds;
}
