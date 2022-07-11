package dev.intermediatebox.flat.repository;

import dev.intermediatebox.JpaHibernateApplication;
import dev.intermediatebox.flat.entity.Passport;
import dev.intermediatebox.flat.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = JpaHibernateApplication.class)
public class TransactionEntityManagerPersistenceContext {
  @Autowired
  EntityManager em;

  @Test
  // @Transactional means that if any step inside transactionEntityManagerPersistenceContextTest function fails, the whole transaction is rolled back
  // @Transactional also creates a Persistence Context; The Persistence Context tracks changes and provides access to the database (e.g. student.getPassport() will hit the database)
  // If @Transactional is not used, then a transaction is opened and immediately closed after em.find => student.getPassport() will fail
  // In Hibernate terminology, Session == Persistence Context
  // Session and Session Factory originate from Hibernate and when working with JPA, are replaced with EntityManager and Persistence Context
  @Transactional
  public void transactionEntityManagerPersistenceContextTest() {
    //Database Operation 1 - Retrieve student
    Student student = em.find(Student.class, 20001L);
    // Persistence Context at this point contains student
    // Interaction with Persistence Context is through EntityManager

    //Database Operation 2 - Retrieve passport
    Passport passport = student.getPassport();
    // Persistence Context at this point contains student + passport

    //Database Operation 3 - update passport
    passport.setNumber("passport 11");
    // Persistence Context at this point contains student + passport with new number

    //Database Operation 4 - update student
    student.setName("student 1 - updated");
    // Persistence Context at this point contains student with new name + passport with new number

    assertEquals("passport 11", passport.getNumber());
    assertEquals("student 1 - updated", student.getName());
  }
}
