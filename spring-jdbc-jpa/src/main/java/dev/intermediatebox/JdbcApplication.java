package dev.intermediatebox;

import dev.intermediatebox.entity.Person;
import dev.intermediatebox.jdbc.PersonJbdcDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

//@SpringBootApplication
public class JdbcApplication implements CommandLineRunner {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	PersonJbdcDao dao;

	public static void main(String[] args) {
		SpringApplication.run(JdbcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		log.info("All users -> {}", dao.findAll());

		log.info("User id 10001 -> {}", dao.findById(10001));

		log.info("Deleting 10002 -> No of Rows Deleted - {}",
				dao.deleteById(10002));

		log.info("Inserting 10004 -> {}",
				dao.insert(new Person(10004, "Tara", "Berlin", new Date())));

		log.info("Update 10003 -> {}",
				dao.update(new Person(10003, "Pieter", "Utrecht", new Date())));
	}
}
