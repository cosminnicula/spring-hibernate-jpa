package dev.intermediatebox.inheritance.singletable.entity;

import lombok.NoArgsConstructor;

import javax.persistence.Table;
import java.math.BigDecimal;

//@Entity
@Table(name = "full_time_employee")

@NoArgsConstructor
public class FullTimeEmployeeSingleTable extends EmployeeSingleTable {
	public FullTimeEmployeeSingleTable(String name, BigDecimal salary) {
		super(name);
		this.salary = salary;
	}

	private BigDecimal salary;
}
