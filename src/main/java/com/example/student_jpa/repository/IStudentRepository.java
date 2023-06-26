package com.example.student_jpa.repository;
import com.example.student_jpa.model.Student;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IStudentRepository extends PagingAndSortingRepository<Student, Long> {
//    @Query(value = "SELECT * FROM students " +
//            "WHERE (:name IS NULL OR LOWER(name) LIKE CONCAT('%', LOWER(:name), '%')) " +
//            "   AND (:age IS NULL OR :age = age)", nativeQuery = true)
    @Query(value = "SELECT * FROM students " +
            "WHERE (:name = '' OR LOWER(name) LIKE CONCAT('%', LOWER(:name), '%')) " +
            "   AND (:classId = 0 OR classes_id = :classId)", nativeQuery = true)
    Page<Student> search(Pageable pageable, @Param("name") String name, @Param("classId") Long classId);
    List<Student> findAllByOrderByPointAsc();
    List<Student> findAllByOrderByAgeAsc();
    List<Student> findAllByClasses_Id(Long id);
    Page<Student> findStudentByClasses_Id(Pageable pageable, Long id);

}
