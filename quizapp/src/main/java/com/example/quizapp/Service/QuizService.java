package com.example.quizapp.Service;

import com.example.quizapp.Entity.Question;
import com.example.quizapp.Entity.QuestionWrapper;
import com.example.quizapp.Entity.Quiz;
import com.example.quizapp.Repository.QuestionRepository;
import com.example.quizapp.Repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizRepository quizRepository;
    @Autowired
    QuestionRepository questionRepository;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        List<Question> questions = questionRepository.findRandomQuestionByCategory(category, numQ);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizRepository.save(quiz);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Optional<Quiz> quiz = quizRepository.findById(id);
        List<Question> questionDb = quiz.get().getQuestions();
        List<QuestionWrapper> questionToUser = new ArrayList<>();
        for (Question q : questionDb) {
            QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
            questionToUser.add(qw);
        }
        return new ResponseEntity<>(questionToUser, HttpStatus.OK);
    }
}
