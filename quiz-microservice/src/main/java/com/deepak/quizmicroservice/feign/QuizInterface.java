package com.deepak.quizmicroservice.feign;

import com.deepak.quizmicroservice.models.EachAnswer;
import com.deepak.quizmicroservice.models.QuestionWrapper;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {
    @GetMapping("question/getQuestionsIdsForQuiz")
    public ResponseEntity<List<Integer>> getQuestionsIdsForQuiz(@RequestParam String category, @RequestParam int noOfQuestions);
    @PostMapping("question/getQuestionsForQuiz")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsForQuiz(List<Integer> questionIds);
    @GetMapping("question/getScore")
    public ResponseEntity<Integer> calculateScore(@RequestBody List<EachAnswer> answers);
}
