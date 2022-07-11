package dev.intermediatebox.flat.repository;

import dev.intermediatebox.flat.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

// Manage Courses using Spring Data JPA
@RepositoryRestResource(path = "course") // see http://localhost:8080/courses
public interface CourseSpringDataJPARepository extends JpaRepository<Course, Long> {
  List<Course> findByName(String name);

  List<Course> countByName(String name);

  List<Course> findByNameOrderById(String name);

  List<Course> deleteByName(String name);

  @Query("Select c From Course c where name like 'c%'")
  List<Course> courseWith100StepsInName();

  @Query(value = "Select * From Course c where name like 'c%'", nativeQuery = true)
  List<Course> courseWith100StepsInNameUsingNativeQuery();

  @Query(name = "query_get_all_courses")
  List<Course> courseWith100StepsInNameUsingNamedQuery();
}
