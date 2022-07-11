package dev.intermediatebox.inheritance.tableperclass.entity;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

//@Entity
@Table(name = "part_time_employee")

@NoArgsConstructor
public class PartTimeEmployeeTablePerClass extends EmployeeTablePerClass {
	public PartTimeEmployeeTablePerClass(String name, BigDecimal hourlyWage) {
		super(name);
		this.hourlyWage = hourlyWage;
	}

	private BigDecimal hourlyWage;
}
