package com.deepak.quizmicroservice.controller;
import com.deepak.quizmicroservice.models.EachAnswer;
import com.deepak.quizmicroservice.models.QuestionWrapper;
import com.deepak.quizmicroservice.models.Quiz;
import com.deepak.quizmicroservice.models.QuizDto;
import com.deepak.quizmicroservice.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
@CrossOrigin(origins = "http://localhost:5173")
public class QuizController {
    @Autowired
    private QuizService quizService;
    @PostMapping("create")
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<Quiz> createQuiz(@RequestBody QuizDto dto) {
        return quizService.createQuiz(dto.getCategory(), dto.getNoOfQuestions(), dto.getQuizTitle());
    }
    @GetMapping("getQuiz/{id}")
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<List<QuestionWrapper>> getQuizById(@PathVariable int id) {
        return quizService.getQuizById(id);
    }
    @PostMapping("submit")
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<Integer> calculateResult(@RequestBody List<EachAnswer> answers) {
        return quizService.calculateResult(answers);
    }
}
