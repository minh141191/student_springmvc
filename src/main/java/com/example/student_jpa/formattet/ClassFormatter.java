package com.example.student_jpa.formattet;

import com.example.student_jpa.model.Classes;
import com.example.student_jpa.service.classes.IClassService;
import org.springframework.format.Formatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;
import java.util.Optional;

@Component
public class ClassFormatter implements Formatter<Classes> {
    private IClassService classService;

    @Autowired
    public ClassFormatter(IClassService classService) {
        this.classService = classService;
    }

    @Override
    public Classes parse(String text, Locale locale) {
        Optional<Classes> provinceOptional = classService.findById(Long.parseLong(text));
        return provinceOptional.orElse(null);
    }

    @Override
    public String print(Classes object, Locale locale) {
        return "[" + object.getId() + ", " + object.getName() + "]";

    }

}