package dev.intermediatebox.flat.repository;

import dev.intermediatebox.flat.entity.Course;
import dev.intermediatebox.flat.entity.Review;
import dev.intermediatebox.flat.entity.ReviewRating;
import dev.intermediatebox.flat.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

// Manage Courses using JPA
@Repository
@Transactional
@Slf4j
public class CourseRepository {
  @Autowired
  private EntityManager em;

  public Course findById(Long id) {
    Course course = em.find(Course.class, id);
    log.info("Course -> {}", course);
    return course;
  }

  public void jpqlFindAll() {
    TypedQuery<Course> query = em.createNamedQuery("query_get_all_courses", Course.class);

    List<Course> results = query.getResultList();

    log.info("Select c From Course c -> {}", results);
  }

  public void jpqlWhere() {
    TypedQuery<Course> query = em.createQuery("Select c From Course c where name like 'c%'", Course.class);

    List<Course> resultList = query.getResultList();

    log.info("Select c From Course c where name like 'c%'-> {}", resultList);
  }

  public void jpqlCoursesWithoutStudents() {
    TypedQuery<Course> query = em.createQuery("Select c from Course c where c.students is empty", Course.class);
    List<Course> resultList = query.getResultList();
    log.info("Results -> {}", resultList);
  }

  public void jpqlCoursesWithAtleast2Students() {
    TypedQuery<Course> query = em.createQuery("Select c from Course c where size(c.students) >= 2", Course.class);
    List<Course> resultList = query.getResultList();
    log.info("Results -> {}", resultList);
  }

  public void jpqlCoursesOrderedByStudents() {
    TypedQuery<Course> query = em.createQuery("Select c from Course c order by size(c.students) desc", Course.class);
    List<Course> resultList = query.getResultList();
    log.info("Results -> {}", resultList);
  }

  public void jpqlStudentsWithPassportsInACertainPattern() {
    TypedQuery<Student> query = em.createQuery("Select s from Student s where s.passport.number like '%course%'", Student.class);
    List<Student> resultList = query.getResultList();
    log.info("Results -> {}", resultList);
  }

  public void innerJoin() {
    Query query = em.createQuery("Select c, s from Course c JOIN c.students s");
    List<Object[]> resultList = query.getResultList();
    log.info("Results Size -> {}", resultList.size());
    for (Object[] result : resultList) {
      log.info("Course{} Student{}", result[0], result[1]);
    }
  }

  public void leftJoin() {
    Query query = em.createQuery("Select c, s from Course c LEFT JOIN c.students s");
    List<Object[]> resultList = query.getResultList();
    log.info("Results Size -> {}", resultList.size());
    for (Object[] result : resultList) {
      log.info("Course{} Student{}", result[0], result[1]);
    }
  }

  // outer join
  public void crossJoin() {
    Query query = em.createQuery("Select c, s from Course c, Student s");
    List<Object[]> resultList = query.getResultList();
    log.info("Results Size -> {}", resultList.size());
    for (Object[] result : resultList) {
      log.info("Course{} Student{}", result[0], result[1]);
    }
  }

  public void deleteById(Long id) {
    Course course = findById(id);
    em.remove(course);
  }



  public Course save(Course course) {
    if (course.getId() == null) {
      em.persist(course);
    } else {
      em.merge(course);
    }
    return course;
  }

  public void nativeQueriesBasic() {
    Query query = em.createNativeQuery("SELECT * FROM COURSE", Course.class);
    List resultList = query.getResultList();
    log.info("SELECT * FROM COURSE  -> {}", resultList);
  }

  public void nativeQueriesBasicWithParameter() {
    Query query = em.createNativeQuery("SELECT * FROM COURSE where id = ?", Course.class);
    query.setParameter(1, 10001L);
    List resultList = query.getResultList();
    log.info("SELECT * FROM COURSE  where id = ? -> {}", resultList);
  }

  public void nativeQueriesBasicWithNamedParameter() {
    Query query = em.createNativeQuery("SELECT * FROM COURSE where id = :id", Course.class);
    query.setParameter("id", 10001L);
    List resultList = query.getResultList();
    log.info("SELECT * FROM COURSE  where id = :id -> {}", resultList);
  }

  public void nativeQueriesToUpdate() {
    Query query = em.createNativeQuery("Update COURSE set last_updated_date=sysdate()");
    int noOfRowsUpdated = query.executeUpdate();
    log.info("noOfRowsUpdated  -> {}", noOfRowsUpdated);
  }

  public void addHardcodedReviewsForCourse() {
    // get the course
    Course course = findById(10003L);
    log.info("course.getReviews() -> {}", course.getReviews());

    // add 2 reviews to it
    Review review1 = new Review(ReviewRating.FIVE, "Great Hands-on Stuff.");
    Review review2 = new Review(ReviewRating.FIVE, "Hatsoff.");

    // set the bidirectional relationship
    course.addReview(review1);
    review1.setCourse(course);

    // set the bidirectional relationship
    course.addReview(review2);
    review2.setCourse(course);

    // persist only the reviews, as no changes were made to the course itself
    em.persist(review1);
    em.persist(review2);
  }

  public void addReviewsForCourse(Long courseId, List<Review> reviews) {
    Course course = findById(courseId);
    log.info("course.getReviews() -> {}", course.getReviews());
    for (Review review : reviews) {
      //setting the relationship
      course.addReview(review);
      review.setCourse(course);
      em.persist(review);
    }
  }
}