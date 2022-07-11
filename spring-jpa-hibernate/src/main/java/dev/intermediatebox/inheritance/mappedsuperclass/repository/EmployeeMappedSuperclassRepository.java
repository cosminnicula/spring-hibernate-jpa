package dev.intermediatebox.inheritance.mappedsuperclass.repository;

import dev.intermediatebox.inheritance.mappedsuperclass.entity.EmployeeMappedSuperclass;
import dev.intermediatebox.inheritance.mappedsuperclass.entity.FullTimeEmployeeMappedSuperclass;
import dev.intermediatebox.inheritance.mappedsuperclass.entity.PartTimeEmployeeMappedSuperclass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Transactional

@Slf4j
public class EmployeeMappedSuperclassRepository {
	@Autowired
	EntityManager em;

	public void insert(EmployeeMappedSuperclass employeeMappedSuperclass) {
		em.persist(employeeMappedSuperclass);
	}

	public List<PartTimeEmployeeMappedSuperclass> retrieveAllPartTimeEmployees() {
		return em.createQuery("select e from PartTimeEmployeeMappedSuperclass e", PartTimeEmployeeMappedSuperclass.class).getResultList();
	}

	public List<FullTimeEmployeeMappedSuperclass> retrieveAllFullTimeEmployees() {
		return em.createQuery("select e from FullTimeEmployeeMappedSuperclass e", FullTimeEmployeeMappedSuperclass.class).getResultList();
	}
}