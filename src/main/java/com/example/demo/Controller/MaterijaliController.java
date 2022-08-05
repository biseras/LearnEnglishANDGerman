package com.example.demo.Controller;

import com.example.demo.Model.Materijali;
import com.example.demo.Service.MaterijaliService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/materijali")
public class MaterijaliController {
    private final MaterijaliService materijaliService;

    public MaterijaliController(MaterijaliService materijaliService) {
        this.materijaliService = materijaliService;
    }

    @GetMapping
    public String getProductPage(@RequestParam(required = false) String error, Model model) {
//         if (error != null && !error.isEmpty()) {
//        //   model.addAttribute("hasError", true);
//            model.addAttribute("error", error);
//         }
        List<Materijali> materijali = this.materijaliService.listAll();
        model.addAttribute("materijali", materijali);
        model.addAttribute("bodyContent", "materijali");
        return "master-template";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        this.materijaliService.deleteById(id);
        return "redirect:/materijali";
    }

    @GetMapping("/edit-form/{id}")
    public String editProductPage(@PathVariable Long id, Model model) {
        if (this.materijaliService.findById(id).isPresent()) {
//            List<Materijali> materijalis = this.materijaliService.findAll();
//            model.addAttribute("materijali", materijalis);
            Materijali materijali = this.materijaliService.findById(id).get();
            model.addAttribute("materijali", materijali);
            model.addAttribute("bodyContent", "add-predmet");
            return "master-template";
        }
        return "redirect:/predmet?error=UcenikNotFound";
    }

    @GetMapping("/add-form")
    public String addProductPage(Model model) {
//        List<Materijali> profesors = this.profesorRepository.findAll();
//        model.addAttribute("profesor", profesors);
        model.addAttribute("bodyContent", "add-materijali");
        return "master-template";
    }

    @PostMapping("/add")
    public String saveProduct(
            @RequestParam(required = false) Long id,
            @RequestParam String naslov,
            @RequestParam String opis,
            @RequestParam String link) {
        if (id != null) {
            this.materijaliService.edit(id, naslov, opis, link);
        } else {
            this.materijaliService.save(naslov, opis, link);
        }
        return "redirect:/materijali";
    }
}
