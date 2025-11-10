package com.example.quizapp.Controller;


import com.example.quizapp.Entity.Question;
import com.example.quizapp.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("/allQuestions")
    public ResponseEntity<List<Question>> getAllQuestion() {
        List<Question> questions = questionService.getAllQuestions();
        if (questions.isEmpty()) {
            return new ResponseEntity<>(questions, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(questions, HttpStatus.OK);
        }
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Question>> getQuestionByCategory(@PathVariable String category) {
        List<Question> categorys = questionService.getQuestionByCategory(category);
        if (categorys != null && categorys.equals(category)) {
            return new ResponseEntity<>(categorys, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(categorys, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addQuestions(@RequestBody Question question) {
        try {
            Question questions = questionService.addQuestion(question);
            return new ResponseEntity<>("questions", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateQuestion(@PathVariable int id, @RequestBody Question question) {
        Question updatequestion = questionService.updateQuestion(id, question);
        if (updatequestion != null) {
            return new ResponseEntity<>("QuestionUpdated", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Something Went wrong", HttpStatus.BAD_REQUEST);

        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable int id) {
        Optional<Question> delete = questionService.finById(id);
        if (delete.isPresent()) {
            questionService.deleteById(id);
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
        }
    }
}
