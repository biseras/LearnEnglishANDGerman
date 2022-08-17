package com.example.demo.Service.Implementation;

import com.example.demo.Model.Question;
import com.example.demo.Model.QuestionForm;
import com.example.demo.Repository.QuestionRepository;
import com.example.demo.Service.QuizService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class QuizServiceImpl implements QuizService {
    private final QuestionRepository questionRepository;

    @Autowired
    QuestionForm questionForm;

    @Override
    public QuestionForm getQuestionsForQuiz() {
        List<Question> questions = this.questionRepository.findAll();
        List<Question> listWith3Questions = new ArrayList<>();
        Random random = new Random();
        for(int i = 0; i < 3; i++){
            Integer number = random.nextInt(questions.size());
            listWith3Questions.add(questions.get(number));
            questions.remove(questions.get(number));
        }
        this.questionForm.setQuestions(listWith3Questions);
        return questionForm;
    }

    @Override
    public List<Question> findAll() {
        return this.questionRepository.findAll();
    }

    @Override
    public Optional<Question> add(String title, String optionA, String optionB, String optionC, Integer answer) {
        return Optional.of(this.questionRepository.save(new Question(title, optionA, optionB, optionC, answer)));
    }

    @Override
    public int getResult(QuestionForm questionForm) {
        int correct = 0;

        for(Question p : questionForm.getQuestions())
        {
            if(p.getAns() == p.getChose())
                correct++;
        }
        return correct;
    }
}
