package com.example.demo.Controller;

import com.example.demo.Model.Question;
import com.example.demo.Model.QuestionForm;
import com.example.demo.Model.Result;
import com.example.demo.Service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/kviz")
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @Autowired
    QuestionForm questionForm;

    @ModelAttribute("quiz")
    public QuestionForm getQuestionForm(){
        return questionForm;
    }

    @GetMapping
    public String kviz(Model model){
        QuestionForm questionForm1 = quizService.getQuestionsForQuiz();
        model.addAttribute("bodyContent", "kviz");
        model.addAttribute("qForm", questionForm1);
        return "master-template";
    }

    @GetMapping("/dodadi-prasanje")
    public String dodadiPrasanjeForma(Model model){
        model.addAttribute("bodyContent", "dodadiPrasanje");
        return "master-template";
    }

    @PostMapping("/dodadi-prasanje")
    public String dodadiPrasanje(@RequestParam String title,
                                 @RequestParam String optionA,
                                 @RequestParam String optionB,
                                 @RequestParam String optionC,
                                 @RequestParam Integer ans){
        this.quizService.add(title, optionA, optionB, optionC, ans);
        return "redirect:/kviz/prasanja";
    }

    @GetMapping("/prasanja")
    public String sitePrasanja(Model model){
        List<Question> questions = this.quizService.findAll();
        model.addAttribute("bodyContent", "siteprasanja");
        model.addAttribute("prasanja", questions);
        return "master-template";
    }

    @GetMapping("/rezultati")
    public String rezultati(@RequestParam(required = false) String error, @RequestParam(required = false) String poeni, Model model){
        if(error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        if(poeni != null && !poeni.isEmpty()) {
            model.addAttribute("zavrseno", true);
            model.addAttribute("poeni", poeni);
        }
        model.addAttribute("bodyContent", "rezultati");
        return "master-template";
    }

    @PostMapping
    public String resiKviz(@ModelAttribute QuestionForm questionForm, Model model){
        int poeni = quizService.getResult(questionForm);
        model.addAttribute("poeni", poeni);
        return "redirect:/kviz/rezultati?poeni=Imate "+poeni+" poeni osvoeno";
    }
}