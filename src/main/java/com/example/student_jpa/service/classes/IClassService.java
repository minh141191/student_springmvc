package com.example.student_jpa.service.classes;

import com.example.student_jpa.model.Classes;
import com.example.student_jpa.service.IGeneralService;

import java.util.List;

public interface IClassService extends IGeneralService<Classes> {
    List<Integer> quantityStudentByClass();
    List<Integer> sortQuantityStudent();
    List<Classes> sortClassByQuantityStudent();
    List<Double> getAvg();
}
