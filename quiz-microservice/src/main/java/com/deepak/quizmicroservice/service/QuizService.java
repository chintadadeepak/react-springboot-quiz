package com.deepak.quizmicroservice.service;
import com.deepak.quizmicroservice.dao.QuizDao;
import com.deepak.quizmicroservice.feign.QuizInterface;
import com.deepak.quizmicroservice.models.EachAnswer;
import com.deepak.quizmicroservice.models.QuestionWrapper;
import com.deepak.quizmicroservice.models.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    private QuizDao quizDao;
    @Autowired
    private QuizInterface quizInterface;
    public ResponseEntity<Quiz> createQuiz(String category, int noOfQuestions, String quizTitle) {
        List<Integer> questionIds = quizInterface.getQuestionsIdsForQuiz(category, noOfQuestions).getBody();
        Quiz newQuiz = new Quiz();
        newQuiz.setQuizTitle(quizTitle);
        newQuiz.setQuestionIds(questionIds);
        quizDao.save(newQuiz);
        return new ResponseEntity<>(newQuiz, HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizById(int id) {
        Quiz quiz = quizDao.findById(id).get();
        List<Integer> questionIds = quiz.getQuestionIds();
        System.out.println(questionIds);
        List<QuestionWrapper> questionsToUser = quizInterface.getQuestionsForQuiz(questionIds).getBody();
        System.out.println(questionsToUser);
        return new ResponseEntity<>(questionsToUser, HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(List<EachAnswer> answers) {
        Integer counter = quizInterface.calculateScore(answers).getBody();
        return new ResponseEntity<>(counter, HttpStatus.OK);
    }
}
