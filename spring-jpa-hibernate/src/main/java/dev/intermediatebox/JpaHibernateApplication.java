package dev.intermediatebox;

import dev.intermediatebox.flat.entity.*;
import dev.intermediatebox.flat.repository.CourseRepository;
import dev.intermediatebox.flat.repository.CourseRepositoryCriteriaQuery;
import dev.intermediatebox.flat.repository.CourseSpringDataJPARepository;
import dev.intermediatebox.flat.repository.StudentRepository;
import dev.intermediatebox.inheritance.joined.entity.PartTimeEmployeeJoined;
import dev.intermediatebox.inheritance.joined.repository.EmployeeJoinedRepository;
import dev.intermediatebox.inheritance.mappedsuperclass.entity.EmployeeMappedSuperclass;
import dev.intermediatebox.inheritance.mappedsuperclass.entity.PartTimeEmployeeMappedSuperclass;
import dev.intermediatebox.inheritance.mappedsuperclass.repository.EmployeeMappedSuperclassRepository;
import dev.intermediatebox.inheritance.singletable.entity.FullTimeEmployeeSingleTable;
import dev.intermediatebox.inheritance.singletable.entity.PartTimeEmployeeSingleTable;
import dev.intermediatebox.inheritance.singletable.repository.EmployeeSingleTableRepository;
import dev.intermediatebox.inheritance.tableperclass.entity.FullTimeEmployeeTablePerClass;
import dev.intermediatebox.inheritance.tableperclass.entity.PartTimeEmployeeTablePerClass;
import dev.intermediatebox.inheritance.tableperclass.repository.EmployeeTablePerClassRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.Subgraph;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
@Slf4j
@Transactional
public class JpaHibernateApplication implements CommandLineRunner {
	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	CourseSpringDataJPARepository courseSpringDataJPARepository;

	@Autowired
	private CourseRepositoryCriteriaQuery courseRepositoryCriteriaQuery;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private EmployeeSingleTableRepository employeeSingleTableRepository;

	@Autowired
	EmployeeTablePerClassRepository employeeTablePerClassRepository;

	@Autowired
	EmployeeJoinedRepository employeeJoinedRepository;

	@Autowired
	EmployeeMappedSuperclassRepository employeeMappedSuperclassRepository;

	@Autowired
	EntityManager em;

	public static void main(String[] args) {
		SpringApplication.run(JpaHibernateApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		initialize();
	}

	private void initialize() {
//		//  ********CRUD, JPQL, EntityManager, TypedQueries
//		Course course = courseRepository.findById(10001L);
//		log.info("{}", course);

//		courseRepository.deleteById(course.getId());
//
//		courseRepository.save(new Course("course something"));
//
//		playWithEntityManager();
//
//		courseRepository.jpqlFindAll();
//
//		courseRepository.jpqlWhere();

//		courseRepository.jpqlCoursesWithoutStudents();
//
//		courseRepository.jpqlCoursesWithAtleast2Students();
//
//		courseRepository.jpqlCoursesOrderedByStudents();
//
//		courseRepository.jpqlStudentsWithPassportsInACertainPattern();


//		// ********Spring Data JPA
//
//		Optional<Course> course = courseSpringDataJPARepository.findById(10001L);
//		log.info("{}", course.isPresent());

//		Course course = courseSpringDataJPARepository.save(new Course("course 11"));
//		course.setName("course 11 - updateds");
//		courseSpringDataJPARepository.save(course);

//		log.info("Courses -> {} ", courseSpringDataJPARepository.findAll());
//
//		log.info("Count -> {} ", courseSpringDataJPARepository.count());

//		Sort sortByNameAndId = Sort.by(Sort.Direction.DESC, "name").and(Sort.by(Sort.Direction.DESC, "id"));
//		log.info("Courses -> {} ", courseSpringDataJPARepository.findAll(sortByNameAndId));
//
//		PageRequest pageRequest = PageRequest.of(0, 1);
//		log.info("Courses -> {} ", courseSpringDataJPARepository.findAll(pageRequest));

//		// custom query
//		log.info("Courses -> {} ", courseSpringDataJPARepository.findByName("course 1"));



//		// ********Hibernate first level cache
//		Course course = courseRepository.findById(10001L);
//		log.info("Course retrieved -> {} ", course);
//
//		// first level cache: within a PersistenceContext, the data for the entity is cached;
//		// first level cache lives within the boundary of a single transaction
//		// if JpaHibernateApplication would not have been decorated with @Transactional, the second findById would not have been cached,
//		// because each call to findById has a separate transaction boundary (CourseRepository is decorated with @Transactional)
//		// the ideal place where the boundary of a transaction should start is at the Service layer, and not at the Data layer
//		course = courseRepository.findById(10001L);
//		log.info("Course retrieved again -> {} ", course);


//		// ********Hibernate second level cache



//		// ********CriteriaQuery
//		courseRepositoryCriteriaQuery.criteriaQueryFindAll();
//
//		courseRepositoryCriteriaQuery.criteriaQueryWhere();
//
//		courseRepositoryCriteriaQuery.criteriaQueryCoursesWithoutStudents();
//
//		courseRepositoryCriteriaQuery.criteriaQueryInnerJoin();



//		// ********JPQL joins (inner join, left join, outer join)

//		courseRepository.innerJoin();
//
//		courseRepository.leftJoin();
//
//		courseRepository.crossJoin();



//		// ********one-to-one relationship
//		studentRepository.saveStudentWithPassport();
//
//		oneToOneRelationship();
//
//		oneToOneRelationship2();



//		// ********one-to-many relationship
//
//		oneToManyRelationship();
//
//		oneToManyRetrieveReviewsForCourse();
//
//		oneToManyRetrieveCourseForReview();



//		// ********many-to-many relationship
//
//		manyToManyRelationshipRetrieveStudentAndCourses();
//
//		manyToManyInsertHardcodedStudentAndCourse();



//		// ********single table inheritance
//		singleTableInheritanceInsertEmployees();

//		// ********table per class inheritance
//		tablePerClassInheritanceInsertEmployees();



//		// ********joined inheritance
//		joinedInheritanceInsertEmployees();



//		// ********mapped superclass inheritance
//		mappedSuperclassInheritanceInsertEmployees();



//		// ********performance tuning
//		nPlusOne();
		nPlusOneJoinFetch();
	}

	public void playWithEntityManager() {
		// example 1
		Course course1 = new Course("c");
		em.persist(course1);

		// forces course1 to be persisted in the database
		em.flush();

		// this runs withing the current transaction => at the end of playWithEntityManager(), or by explicitly flushing, this change is persisted to database
		course1.setName("course");

		// all changes made up to this point to course1 are reverted to the original state (the state that were in at the beginning of the transaction)
		em.refresh(course1);

		// example 2
		Course course2 = courseRepository.findById(10001L);
		em.detach(course2);

		// calling the setter will no longer cause the change to be reflected at the end of playWithEntityManager(), or by explicitly flushing, because the entity was detached
		course2.setName("something");

		// course2 is not persisted in the database (see above)
		em.flush();

		// example 3
		em.clear(); // this clears everything inside the EntityManger (no entities will be tracked after this point)
	}

	// retrieve student and associated passport
	private void oneToOneRelationship() {
		// one-to-one relationships are by default eagerly fetched => s1 will also contain passport details
		// to change this behavior, annotate passport inside Student with FetchType.LAZY
		Student s1 = studentRepository.findById(20001L);
		log.info("student {}", s1);
		// s1.getPassport() will throw a "no session" exception, because after studentRepository.findById, the session gets ended
		// to solve this problem, annotate with @Transactional
		log.info("student passport {}", s1.getPassport());
	}

	// retrieve passport and associated student
	private void oneToOneRelationship2() {
		Passport passport = em.find(Passport.class, 40001L);
		log.info("passport -> {}", passport);
		log.info("student associated with passport -> {}", passport.getStudent());
	}

	// add reviews to course
	private void oneToManyRelationship() {
		courseRepository.addHardcodedReviewsForCourse();

		List<Review> reviews = new ArrayList<>();
		reviews.add(new Review(ReviewRating.FIVE, "Great Hands-on Stuff."));
		reviews.add(new Review(ReviewRating.FIVE, "Hatsoff."));
		courseRepository.addReviewsForCourse(10003L, reviews);
	}

	private void oneToManyRetrieveReviewsForCourse() {
		Course course = courseRepository.findById(10001L);
		log.info("{}", course.getReviews());
	}

	private void oneToManyRetrieveCourseForReview() {
		Review review = em.find(Review.class, 50001L);
		log.info("{}", review.getCourse());
	}

	private void manyToManyRelationshipRetrieveStudentAndCourses() {
		Student student = em.find(Student.class, 20001L);

		log.info("student -> {}", student);
		log.info("courses -> {}", student.getCourses());
	}

	private void manyToManyInsertHardcodedStudentAndCourse(){
		Student student = new Student("student 5");
		Course course = new Course("course 5");
		manyToManyInsertStudentAndCourse(student, course);
		em.persist(student);
	}

	private void manyToManyInsertStudentAndCourse(Student student, Course course){
		student.addCourse(course);
		course.addStudent(student);

		em.persist(student);
		em.persist(course);
	}

	private void singleTableInheritanceInsertEmployees() {
		employeeSingleTableRepository.insert(new PartTimeEmployeeSingleTable("employee 1", new BigDecimal("65")));
		employeeSingleTableRepository.insert(new FullTimeEmployeeSingleTable("employee 2", new BigDecimal("10000")));

		log.info("All Employees -> {}", employeeSingleTableRepository.retrieveAllEmployees());
	}

	private void tablePerClassInheritanceInsertEmployees() {
		employeeTablePerClassRepository.insert(new PartTimeEmployeeTablePerClass("employee 1", new BigDecimal("65")));
		employeeTablePerClassRepository.insert(new FullTimeEmployeeTablePerClass("employee 2", new BigDecimal("10000")));

		log.info("All Employees -> {}", employeeTablePerClassRepository.retrieveAllEmployees());
	}

	private void joinedInheritanceInsertEmployees() {
		employeeJoinedRepository.insert(new PartTimeEmployeeJoined("employee 1", new BigDecimal("65")));
		employeeJoinedRepository.insert(new PartTimeEmployeeJoined("employee 2", new BigDecimal("10000")));

		log.info("All Employees -> {}", employeeJoinedRepository.retrieveAllEmployees());
	}

	private void mappedSuperclassInheritanceInsertEmployees() {
		employeeMappedSuperclassRepository.insert(new PartTimeEmployeeMappedSuperclass("employee 1", new BigDecimal("65")));
		employeeMappedSuperclassRepository.insert(new PartTimeEmployeeMappedSuperclass("employee 2", new BigDecimal("10000")));

		log.info("Full-time Employees -> {}", employeeMappedSuperclassRepository.retrieveAllFullTimeEmployees());
		log.info("Part-time Employees -> {}", employeeMappedSuperclassRepository.retrieveAllPartTimeEmployees());
	}

	private void nPlusOne() {
		EntityGraph<Course> entityGraph = em.createEntityGraph(Course.class);
		Subgraph<Object> subGraph = entityGraph.addSubgraph("students");

		List<Course> courses = em
				.createNamedQuery("query_get_all_courses", Course.class)
				.setHint("javax.persistence.loadgraph", entityGraph)
				.getResultList();

		for(Course course:courses){
			log.info("Course (n+1) -> {} Students -> {}",course, course.getStudents());
		}
	}

	private void nPlusOneJoinFetch() {
		List<Course> courses = em
				.createNamedQuery("query_get_all_courses_join_fetch", Course.class)
				.getResultList();
		for(Course course:courses){
			log.info("Course (n+1 join fetch) -> {} Students -> {}",course, course.getStudents());
		}
	}
}
