package com.deepak.questionmicroservice.controller;


import com.deepak.questionmicroservice.models.EachAnswer;
import com.deepak.questionmicroservice.models.Question;
import com.deepak.questionmicroservice.models.QuestionWrapper;
import com.deepak.questionmicroservice.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
@CrossOrigin(origins = "http://localhost:5173")
public class QuestionController {
    @Autowired
    private Environment environment;
    @Autowired
    private QuestionService questionService;
    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions() {
        return questionService.getAllQuestions();
    }
    @PostMapping("addQuestion")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question) {
        return questionService.addQuestion(question);
    }
    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category) {
        return questionService.getQuestionsByCategory(category);
    }

    @GetMapping("getQuestionsIdsForQuiz")
    @CrossOrigin
    public ResponseEntity<List<Integer>> getQuestionsIdsForQuiz(@RequestParam String category, @RequestParam int noOfQuestions) {
        return questionService.getQuestionsIdsForQuiz(category, noOfQuestions);
    }

    @PostMapping("getQuestionsForQuiz")
    @CrossOrigin
    public ResponseEntity<List<QuestionWrapper>> getQuestionsForQuiz(@RequestBody List<Integer> questionIds) {
        System.out.println(environment.getProperty("local.server.port"));
        return questionService.getQuestionsForQuiz(questionIds);
    }

    @PostMapping("getScore")
    @CrossOrigin
    public ResponseEntity<Integer> calculateScore(@RequestBody List<EachAnswer> answers) {
        return questionService.calculateScore(answers);
    }
}
