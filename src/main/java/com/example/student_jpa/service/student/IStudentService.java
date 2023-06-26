package com.example.student_jpa.service.student;
import com.example.student_jpa.model.Student;
import com.example.student_jpa.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface IStudentService extends IGeneralService<Student> {
    Page<Student> search(Pageable pageable, String name, Long classId);
    List<Student> findAllByOrderByPointAsc();
    List<Student> findAllByOrderByAgeAsc();
    List<Student> findAllByClasses_Id(Long id);
    Page<Student> findAll(Pageable pageable);
    Page<Student> findStudentByClasses_Id(Pageable pageable, Long id);
}
