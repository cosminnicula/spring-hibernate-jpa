package dev.intermediatebox.inheritance.singletable.entity;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

//@Entity
@Table(name = "employee")

@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

// employee
// ---------------------------------------------------------------------
// | dtype                | id  | name        | hourly_wage | salary   |
// ---------------------------------------------------------------------
// | PartTimeEmployee     | 1   | employee 1  | 65          | null     |
// | FullTimeEmployee     | 2   | employee 2  | null        | 10500    |
// ---------------------------------------------------------------------

// SINGLE_TABLE is the default inheritance strategy
// with SINGLE_TABLE strategy, the employee table contains an additional dtype column which specifies the concrete type (PartTimeEmployee / FullTimeEmployee), together
// with all the column of the concrete types (hourly_wage, salary)
// with SINGLE_TABLE strategy, there are no joins
// Disadvantages:
// - data integrity: columns are nullable
// Advantages:
// - performance
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
// @DiscriminatorColumn changes the name of dtype column to employee_type
@DiscriminatorColumn(name = "EmployeeType")
public abstract class EmployeeSingleTable {
  @Id
  @GeneratedValue
  private Long id;

  @Column(nullable = false)
  private String name;

  public EmployeeSingleTable(String name) {
    this.name = name;
  }
}
