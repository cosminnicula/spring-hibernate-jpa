package dev.intermediatebox.inheritance.tableperclass.repository;

import dev.intermediatebox.inheritance.tableperclass.entity.EmployeeTablePerClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Transactional

@Slf4j
public class EmployeeTablePerClassRepository {
	@Autowired
	EntityManager em;

	public void insert(EmployeeTablePerClass employeeTablePerClass) {
		em.persist(employeeTablePerClass);
	}

	public List<EmployeeTablePerClass> retrieveAllEmployees() {
		return em.createQuery("select e from EmployeeTablePerClass e", EmployeeTablePerClass.class).getResultList();
	}
}