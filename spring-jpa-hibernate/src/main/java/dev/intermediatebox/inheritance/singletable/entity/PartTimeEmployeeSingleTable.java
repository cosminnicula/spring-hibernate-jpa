package dev.intermediatebox.inheritance.singletable.entity;

import lombok.NoArgsConstructor;

import javax.persistence.Table;
import java.math.BigDecimal;

//@Entity
@Table(name = "part_time_employee")

@NoArgsConstructor
public class PartTimeEmployeeSingleTable extends EmployeeSingleTable {
	public PartTimeEmployeeSingleTable(String name, BigDecimal hourlyWage) {
		super(name);
		this.hourlyWage = hourlyWage;
	}

	private BigDecimal hourlyWage;
}
