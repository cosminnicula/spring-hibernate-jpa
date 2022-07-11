package dev.intermediatebox.inheritance.mappedsuperclass.entity;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "part_time_employee")

@NoArgsConstructor
public class PartTimeEmployeeMappedSuperclass extends EmployeeMappedSuperclass {
	public PartTimeEmployeeMappedSuperclass(String name, BigDecimal hourlyWage) {
		super(name);
		this.hourlyWage = hourlyWage;
	}

	private BigDecimal hourlyWage;
}
