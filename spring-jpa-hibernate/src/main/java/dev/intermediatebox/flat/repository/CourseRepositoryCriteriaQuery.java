package dev.intermediatebox.flat.repository;

import dev.intermediatebox.flat.entity.Course;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
@Slf4j
public class CourseRepositoryCriteriaQuery {
  @Autowired
  private EntityManager em;

  public void criteriaQueryFindAll() {
    // "Select c From Course c"

    // 1. Use Criteria Builder to create a Criteria Query returning the
    // expected result object
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<Course> cq = cb.createQuery(Course.class);

    // 2. Define roots for tables which are involved in the query
    Root<Course> courseRoot = cq.from(Course.class);

    // 3. Define Predicates etc. using Criteria Builder

    // 4. Add Predicates etc. to the Criteria Query

    // 5. Build the TypedQuery using the entity manager and criteria query
    TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));

    List<Course> resultList = query.getResultList();

    log.info("Typed Query -> {}", resultList);
  }

  public void criteriaQueryWhere() {
    // "Select c From Course c where name like 'c%' "

    // 1. Use Criteria Builder to create a Criteria Query returning the
    // expected result object
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<Course> cq = cb.createQuery(Course.class);

    // 2. Define roots for tables which are involved in the query
    Root<Course> courseRoot = cq.from(Course.class);

    // 3. Define Predicates etc. using Criteria Builder
    Predicate likeC = cb.like(courseRoot.get("name"), "c%");

    // 4. Add Predicates etc. to the Criteria Query
    cq.where(likeC);

    // 5. Build the TypedQuery using the entity manager and criteria query
    TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));

    List<Course> resultList = query.getResultList();

    log.info("Typed Query -> {}", resultList);
  }

  public void criteriaQueryCoursesWithoutStudents() {
    // "Select c From Course c where c.students is empty"

    // 1. Use Criteria Builder to create a Criteria Query returning the
    // expected result object
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<Course> cq = cb.createQuery(Course.class);

    // 2. Define roots for tables which are involved in the query
    Root<Course> courseRoot = cq.from(Course.class);

    // 3. Define Predicates etc. using Criteria Builder
    Predicate studentsIsEmpty = cb.isEmpty(courseRoot.get("students"));

    // 4. Add Predicates etc. to the Criteria Query
    cq.where(studentsIsEmpty);

    // 5. Build the TypedQuery using the entity manager and criteria query
    TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));

    List<Course> resultList = query.getResultList();

    log.info("Typed Query -> {}", resultList);
  }

  public void criteriaQueryInnerJoin() {
    // "Select c From Course c join c.students s"

    // 1. Use Criteria Builder to create a Criteria Query returning the
    // expected result object
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<Course> cq = cb.createQuery(Course.class);

    // 2. Define roots for tables which are involved in the query
    Root<Course> courseRoot = cq.from(Course.class);

    // 3. Define Predicates etc using Criteria Builder
    Join<Object, Object> join = courseRoot.join("students");

    // 4. Add Predicates etc to the Criteria Query

    // 5. Build the TypedQuery using the entity manager and criteria query
    TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));

    List<Course> resultList = query.getResultList();

    log.info("Typed Query -> {}", resultList);
  }

  public void left_join() {
    // "Select c From Course c left join c.students s"

    // 1. Use Criteria Builder to create a Criteria Query returning the
    // expected result object
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<Course> cq = cb.createQuery(Course.class);

    // 2. Define roots for tables which are involved in the query
    Root<Course> courseRoot = cq.from(Course.class);

    // 3. Define Predicates etc. using Criteria Builder
    Join<Object, Object> join = courseRoot.join("students", JoinType.LEFT);

    // 4. Add Predicates etc. to the Criteria Query

    // 5. Build the TypedQuery using the entity manager and criteria query
    TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));

    List<Course> resultList = query.getResultList();

    log.info("Typed Query -> {}", resultList);
  }
}