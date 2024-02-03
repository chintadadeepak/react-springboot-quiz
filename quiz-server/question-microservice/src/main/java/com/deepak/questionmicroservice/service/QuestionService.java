package com.deepak.questionmicroservice.service;
import com.deepak.questionmicroservice.dao.QuestionDao;
import com.deepak.questionmicroservice.models.EachAnswer;
import com.deepak.questionmicroservice.models.Question;
import com.deepak.questionmicroservice.models.QuestionWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class QuestionService {
    @Autowired
    private QuestionDao questionDao;
    public ResponseEntity<List<Question>> getAllQuestions() {
        return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
    }
    public ResponseEntity<Question> addQuestion(Question question) {
        return new ResponseEntity<>(questionDao.save(question), HttpStatus.CREATED);
    }
    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.OK);
    }

    public ResponseEntity<List<Integer>> getQuestionsIdsForQuiz(String category, int noOfQuestions) {
        List<Integer> questionIdsForQuiz = questionDao.findByRandomQuestionsByCategory(category, noOfQuestions);
        return new ResponseEntity<>(questionIdsForQuiz, HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsForQuiz(List<Integer> questionIds) {
        List<QuestionWrapper> actualQuestionsToUser = new ArrayList<>();
        for(Integer id : questionIds) {
            Question eachQuestion = questionDao.findById(id).get();
            actualQuestionsToUser.add(new QuestionWrapper(
                    eachQuestion.getId(), eachQuestion.getQuestionTitle(),
                    eachQuestion.getOption1(), eachQuestion.getOption2(),
                    eachQuestion.getOption3(), eachQuestion.getOption4()
            ));
        }
        return new ResponseEntity<>(actualQuestionsToUser, HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateScore(List<EachAnswer> answers) {
        Integer counter = 0;
        for(EachAnswer answer : answers) {
            if(questionDao.findById(answer.getId()).get().getRightAnswer().equals(answer.getAnswer()))
                counter += 1;
        }
        return new ResponseEntity<>(counter, HttpStatus.OK);
    }
}
