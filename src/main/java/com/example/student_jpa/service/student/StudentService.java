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
    public List<Student> findAllByClasses_Id(Long id) {
        return studentRepository.findAllByClasses_Id(id);
    }


    @Override
    public Page<Student> findAll(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    @Override
    public Page<Student> findStudentByClasses_Id(Pageable pageable, Long id) {
        return studentRepository.findStudentByClasses_Id(pageable, id);
    }

}
