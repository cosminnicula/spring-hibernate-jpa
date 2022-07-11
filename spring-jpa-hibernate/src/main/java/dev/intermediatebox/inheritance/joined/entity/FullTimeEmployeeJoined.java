package dev.intermediatebox.inheritance.joined.entity;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

//@Entity
@Table(name = "full_time_employee")

@NoArgsConstructor
public class FullTimeEmployeeJoined extends EmployeeJoined {
	public FullTimeEmployeeJoined(String name, BigDecimal salary) {
		super(name);
		this.salary = salary;
	}

	private BigDecimal salary;
}
