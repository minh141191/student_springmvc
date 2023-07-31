package com.example.student_jpa.service.classes;

import com.example.student_jpa.model.Classes;
import com.example.student_jpa.model.Student;
import com.example.student_jpa.repository.IClassRepository;
import com.example.student_jpa.repository.IStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ClassService implements IClassService {

    @Autowired
    private IClassRepository classRepository;
    @Autowired
    IStudentRepository studentRepository;
    @Override
    public Iterable<Classes> findAll() {
        return classRepository.findAll();
    }

    @Override
    public Optional<Classes> findById(Long id) {
        return classRepository.findById(id);
    }

    @Override
    public void save(Classes classes) {
        classRepository.save(classes);
    }

    @Override
    public void remove(Long id) {
        List<Student> students = studentRepository.findStudentByClasses_Id(id);
        if (students.isEmpty()) {
            classRepository.deleteById(id);
        } else {
            studentRepository.deleteAll(students);
            classRepository.deleteById(id);
        }

    }

    @Override
    public List<Integer> quantityStudentByClass() {
        return classRepository.quantityStudentByClass();
    }

    @Override
    public List<Integer> sortQuantityStudent() {
        return classRepository.sortQuantityStudent();
    }

    @Override
    public List<Classes> sortClassByQuantityStudent() {
        return classRepository.sortClassByQuantityStudent();
    }

    @Override
    public List<Double> getAvg() {
        return classRepository.getAvg();
    }
}
