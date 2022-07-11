package dev.intermediatebox.flat.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CourseDetails")
@NamedQueries(value = {
    @NamedQuery(name = "query_get_all_courses",
        query = "Select c From Course c"),
    @NamedQuery(name = "query_get_all_courses_join_fetch",
        query = "Select  c  From Course c JOIN FETCH c.students s"),
})

@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

// adding @Cachable, will add second level cache support, which means that Course entities will be cached among multiple transactions
// @Cachable will cache only Courses, and not Reviews or Students
// see L2C puts (adds data to the cache), L2C hits (data is in the cache) and L2C misses (data is not in the cache) logs in the console

// soft delete: by using the @SQLDelete annotation, a delete operation is actually an update operation on is_deleted column
@SQLDelete(sql="update course set is_deleted=true where id=?")
// soft delete: all select queries, except native queries, will filter by is_deleted = false
@Where(clause="is_deleted = false")
public class Course {
  @Id
  @GeneratedValue
  private Long id;

  @Column(nullable = false)
  private String name;

  @UpdateTimestamp
  private LocalDateTime lastUpdatedDate;

  @CreationTimestamp
  private LocalDateTime createdDate;

  // soft delete
  private boolean isDeleted;

  // soft delete
  @PreRemove
  private void preRemove(){
    log.info("Setting isDeleted to True");
    this.isDeleted = true;
  }

  // one course has many reviews
  // @OneToMany has by default LAZY fetch type
  // Even though reviews are LAZY loaded, any call to toString() implies calling course.getReviews() which will hit the database
  @OneToMany(mappedBy="course")
  @Setter(AccessLevel.NONE) // Don't generate setter for reviews, as reviews will be added (addReview) or removed (removeReview)
  private List<Review> reviews = new ArrayList<>();

  @Setter(AccessLevel.NONE) // Don't generate setter for reviews, as reviews will be added (addStudent) or removed (removeStudent)
  // for @ManyToMany is not important who owns the relationship
  // for this particular case, student is the owner of the relationship
  // mappedBy prevents creating two tables: student_courses and course_students; it will only create student_courses
  @ManyToMany(mappedBy="courses")
  private List<Student> students = new ArrayList<>();

  public Course(String name) {
    this.name = name;
  }

  public void addReview(Review review) {
    this.reviews.add(review);
  }

  public void removeReview(Review review) {
    this.reviews.remove(review);
  }

  public void addStudent(Student student) {
    this.students.add(student);
  }

  public void removeStudent(Student student) {
    this.students.remove(student);
  }
}
