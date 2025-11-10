package com.example.quizapp.Service;

import com.example.quizapp.Entity.Question;
import com.example.quizapp.Repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public List<Question> getQuestionByCategory(String category) {
        return questionRepository.findByCategory(category);
    }

    public Question addQuestion(Question question) {
       return questionRepository.save(question);
    }

    public Question updateQuestion(int id, Question question) {
        question.setId(id);
       return questionRepository.save(question);
    }

    public void deleteById(int id) {
        questionRepository.deleteById(id);
    }

    public Optional<Question> finById(int id) {
return questionRepository.findById(id);
    }
}
