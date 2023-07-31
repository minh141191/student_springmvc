package com.example.student_jpa.service.student;
import com.example.student_jpa.model.Student;
import com.example.student_jpa.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IStudentService extends IGeneralService<Student> {
    Page<Student> search(Pageable pageable, String name, Long classId);
    List<Student> findAllByOrderByPointAsc();
    List<Student> findAllByOrderByAgeAsc();
    Page<Student> findAllByPage(Pageable pageable);

}
