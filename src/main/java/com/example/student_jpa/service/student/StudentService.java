package com.example.student_jpa.service.student;

import com.example.student_jpa.model.Student;
import com.example.student_jpa.repository.IStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService implements IStudentService {

    @Autowired
    private IStudentRepository studentRepository;
    @Override
    public Iterable<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Optional<Student> findById(Long id) {
        return studentRepository.findById(id);
    }

    @Override
    public void save(Student student) {
        studentRepository.save(student);
    }


    @Override
    public void remove(Long id) {
        studentRepository.deleteById(id);
    }


    @Override
    public Page<Student> search(Pageable pageable, String name, Long classId) {
        return studentRepository.search(pageable, name, classId);
    }

    @Override
    public List<Student> findAllByOrderByPointAsc() {
        return studentRepository.findAllByOrderByPointAsc();
    }

    @Override
    public List<Student> findAllByOrderByAgeAsc() {
        return studentRepository.findAllByOrderByAgeAsc();
    }

    @Override
    public Page<Student> findAllByPage(Pageable pageable) {
        return studentRepository.findAllByPage(pageable);
    }


}
