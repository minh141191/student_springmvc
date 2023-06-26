package com.example.student_jpa.controller;


import com.example.student_jpa.model.Classes;
import com.example.student_jpa.model.Student;
import com.example.student_jpa.service.StudentExcelExporter;
import com.example.student_jpa.service.classes.IClassService;
import com.example.student_jpa.service.student.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private IStudentService studentService;
    @Autowired
    private IClassService classService;

    @ModelAttribute("classes")
    public Iterable<Classes> classes() {
        return classService.findAll();
    }

    @GetMapping()
    public ModelAndView searchStudents(@RequestParam(required = false, defaultValue = "") String searchName,
                                       @RequestParam(required = false, defaultValue = "0") Long classId,
                                     @PageableDefault(value = 4) Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("/students/list");
        modelAndView.addObject("name", searchName);
        modelAndView.addObject("classId", classId);
        modelAndView.addObject("students", studentService.search(pageable, searchName, classId));
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView CreateForm() {
        ModelAndView modelAndView = new ModelAndView("/students/create");
        modelAndView.addObject("students", new Student());
        return modelAndView;
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Student student) {
        studentService.save(student);
        return "redirect:/students";
    }

    @GetMapping("/update/{id}")
    public ModelAndView updateForm(@PathVariable Long id) {
        Optional<Student> student = studentService.findById(id);
        if (student.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/students/update");
            modelAndView.addObject("students", student.get());
            return modelAndView;
        } else {
            return new ModelAndView("/404");
        }
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Student student) {
        studentService.save(student);
        return "redirect:/students";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        studentService.remove(id);
        return "redirect:/students";
    }

    @GetMapping("/views/{id}")
    public ModelAndView views(@PathVariable Long id) {
        Optional<Student> student = studentService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/students/views");
        modelAndView.addObject("students", student.get());
        return modelAndView;
    }

    @GetMapping("/sort-point")
    public ModelAndView sortByPoint() {
        List<Student> students = studentService.findAllByOrderByPointAsc();
        ModelAndView modelAndView = new ModelAndView("/students/list");
        modelAndView.addObject("students", students);
        return modelAndView;
    }
    @GetMapping("/sort-age")
    public ModelAndView sortByAge() {
        List<Student> students = studentService.findAllByOrderByAgeAsc();
        ModelAndView modelAndView = new ModelAndView("/students/list");
        modelAndView.addObject("students", students);
        return modelAndView;
    }

    @GetMapping("/export-excel")
    public void exportToExcel(HttpServletResponse response, @RequestParam(required = false, defaultValue = "") String searchName,
                              @RequestParam(required = false, defaultValue = "0") Long classId) {
        response.setContentType("application/octet-stream");
        DateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dataFormat.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=student_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
        Pageable pageable = Pageable.ofSize(Integer.MAX_VALUE);
        Page<Student> students = studentService.search(pageable, searchName, classId);
        StudentExcelExporter excelExporter = new StudentExcelExporter(students.toList());
        excelExporter.export(response);
    }
}
