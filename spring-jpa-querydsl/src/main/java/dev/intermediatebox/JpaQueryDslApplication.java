package dev.intermediatebox;

import com.querydsl.jpa.impl.JPAQueryFactory;
import dev.intermediatebox.entity.QStudent;
import dev.intermediatebox.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@SpringBootApplication
@Slf4j
public class JpaQueryDslApplication implements CommandLineRunner {
	@Autowired
	EntityManager em;

	@Bean
	protected JPAQueryFactory queryFactory() {
		return new JPAQueryFactory(em);
	}

	@Autowired
	JPAQueryFactory qf;

	public static void main(String[] args) {
		SpringApplication.run(JpaQueryDslApplication.class, args);
	}

	@Transactional
	@Override
	public void run(String... arg0) throws Exception {
		// Join relationships are managed by JPA; could explicitly be managed by QueryDSL, for non-JPA entities
		List<Student> students = qf.selectFrom(QStudent.student).fetch();
		log.info("Students {} ", students);
	}
}
