package com.example.student_jpa.repository;
import com.example.student_jpa.model.Student;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IStudentRepository extends JpaRepository<Student, Long> {
//    @Query(value = "SELECT * FROM students " +
//            "WHERE (:name IS NULL OR LOWER(name) LIKE CONCAT('%', LOWER(:name), '%')) " +
//            "   AND (:age IS NULL OR :age = age)", nativeQuery = true)
    @Query(value = "SELECT * FROM students " +
            "WHERE (LOWER(name) LIKE CONCAT('%', LOWER(:name), '%')) " +
            "   AND (:classId = 0 OR classes_id = :classId)", nativeQuery = true)
    Page<Student> search(Pageable pageable, @Param("name") String name, @Param("classId") Long classId);
    List<Student> findAllByOrderByPointAsc();
    List<Student> findAllByOrderByAgeAsc();
    List<Student> findStudentByClasses_Id(Long id);
    @Query(value = "select * from students", nativeQuery = true)
    Page<Student> findAllByPage(Pageable pageable);

}
