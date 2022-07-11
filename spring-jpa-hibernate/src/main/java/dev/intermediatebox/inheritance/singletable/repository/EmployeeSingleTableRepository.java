package dev.intermediatebox.inheritance.singletable.repository;

import dev.intermediatebox.inheritance.singletable.entity.EmployeeSingleTable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Transactional

@Slf4j
public class EmployeeSingleTableRepository {
	@Autowired
	EntityManager em;

	public void insert(EmployeeSingleTable employeeSingleTable) {
		em.persist(employeeSingleTable);
	}

	public List<EmployeeSingleTable> retrieveAllEmployees() {
		return em.createQuery("select e from EmployeeSingleTable e", EmployeeSingleTable.class).getResultList();
	}
}