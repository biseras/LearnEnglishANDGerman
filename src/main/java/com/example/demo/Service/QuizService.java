package com.example.demo.Service;

import com.example.demo.Model.Question;
import com.example.demo.Model.QuestionForm;

import java.util.List;
import java.util.Optional;

public interface QuizService {
    QuestionForm getQuestionsForQuiz();

    List<Question> findAll();

    Optional<Question> add(String title, String optionA, String optionB, String optionC, Integer answer);

    int getResult(QuestionForm questionForm);
}
