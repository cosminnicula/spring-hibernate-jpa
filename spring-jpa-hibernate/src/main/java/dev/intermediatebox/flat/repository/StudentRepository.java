package dev.intermediatebox.flat.repository;

import dev.intermediatebox.flat.entity.Passport;
import dev.intermediatebox.flat.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

import javax.persistence.EntityManager;

@Repository
@Transactional
public class StudentRepository {
	@Autowired
	EntityManager em;

	public Student findById(Long id) {
		return em.find(Student.class, id);
	}

	public Student save(Student student) {
		if (student.getId() == null) {
			em.persist(student);
		} else {
			em.merge(student);
		}

		return student;
	}

	public void saveStudentWithPassport() {
		Passport passport = new Passport("Z123456");
		// after calling persist(), the passport is still not persisted in the database, which is ok; passport it's only tracked in the EntityManager
		em.persist(passport);

		Student student = new Student("student 4");
		student.setPassport(passport);
		// same as above
		em.persist(student);

		// only after exiting the function, the two entities are persisted in the database
	}

	public void deleteById(Long id) {
		Student student = findById(id);
		em.remove(student);
	}
}
