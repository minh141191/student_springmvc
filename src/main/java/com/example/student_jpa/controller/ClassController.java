package com.example.student_jpa.controller;

import com.example.student_jpa.model.Classes;
import com.example.student_jpa.service.classes.IClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class ClassController {

    @Autowired
    private IClassService classService;

    @GetMapping("/classes")
    public ModelAndView listClass() {
        ModelAndView modelAndView = new ModelAndView("/classes/list");
        modelAndView.addObject("countStudent", classService.quantityStudentByClass());
        modelAndView.addObject("classes", classService.findAll());
        return modelAndView;
    }

    @GetMapping("/create-class")
    public ModelAndView createForm() {
        ModelAndView modelAndView = new ModelAndView("/classes/create");
        modelAndView.addObject("classes", new Classes());
        return modelAndView;
    }

    @PostMapping("/create-class")
    public String create(@ModelAttribute Classes classes) {
        classService.save(classes);
        return "redirect:/classes";
    }

    @GetMapping("/update-class/{id}")
    public ModelAndView updateForm(@PathVariable Long id) {
        Optional<Classes> classes = classService.findById(id);
        if (classes.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/classes/update");
            modelAndView.addObject("classes", classes.get());
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/404");
            return modelAndView;
        }
    }

    @PostMapping("/update-class")
    public String update(@ModelAttribute Classes classes) {
        classService.save(classes);
        return "redirect:/classes";
    }

    @GetMapping("/sort-class")
    public ModelAndView sortClassAsc() {
        ModelAndView modelAndView = new ModelAndView("/classes/list");
        modelAndView.addObject("classes", classService.sortClassByQuantityStudent());
        modelAndView.addObject("countStudent", classService.sortQuantityStudent());
        return modelAndView;
    }

    @GetMapping("/getAvg")
    public ModelAndView showAvgOfClass() {
        ModelAndView modelAndView = new ModelAndView("/classes/list");
        modelAndView.addObject("countStudent", classService.quantityStudentByClass());
        modelAndView.addObject("classes", classService.findAll());
        modelAndView.addObject("avgPoint", classService.getAvg());
        return modelAndView;
    }
}
