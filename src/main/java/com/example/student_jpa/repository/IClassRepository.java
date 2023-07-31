package com.example.student_jpa.repository;

import com.example.student_jpa.model.Classes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IClassRepository extends JpaRepository<Classes, Long> {
    @Query(value = "select count(s.id) as total_students\n" +
            "from classes c left join students s on c.id = s.classes_id\n" +
            "group by c.id", nativeQuery = true)
    List<Integer> quantityStudentByClass();
    @Query(value = "select count(s.id) as total_students\n" +
            "from classes c left join students s on c.id = s.classes_id\n" +
            "group by c.id\n" +
            "order by total_students", nativeQuery = true)
    List<Integer> sortQuantityStudent();

    @Query(value = "select c.id, c.name, count(s.id) as total_students\n" +
            "from classes c left join students s on c.id = s.classes_id\n" +
            "group by c.id\n" +
            "order by total_students", nativeQuery = true)
    List<Classes> sortClassByQuantityStudent();

    @Query(value = "select avg(point) from students s right join classes c on c.id = s.classes_id group by classes_id;", nativeQuery = true)
    List<Double> getAvg();
}
