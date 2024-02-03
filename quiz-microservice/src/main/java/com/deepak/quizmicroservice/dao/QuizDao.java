package com.deepak.quizmicroservice.dao;
import com.deepak.quizmicroservice.models.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizDao extends JpaRepository<Quiz, Integer> {
}
