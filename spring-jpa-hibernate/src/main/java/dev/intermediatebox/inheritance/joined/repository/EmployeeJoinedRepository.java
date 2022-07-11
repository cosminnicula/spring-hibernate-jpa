package dev.intermediatebox.inheritance.joined.repository;

import dev.intermediatebox.inheritance.joined.entity.EmployeeJoined;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Transactional

@Slf4j
public class EmployeeJoinedRepository {
	@Autowired
	EntityManager em;

	public void insert(EmployeeJoined employeeJoined) {
		em.persist(employeeJoined);
	}

	public List<EmployeeJoined> retrieveAllEmployees() {
		return em.createQuery("select e from EmployeeJoined e", EmployeeJoined.class).getResultList();
	}
}