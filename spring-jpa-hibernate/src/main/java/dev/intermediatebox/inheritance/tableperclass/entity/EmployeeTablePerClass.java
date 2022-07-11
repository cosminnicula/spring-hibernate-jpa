package dev.intermediatebox.inheritance.tableperclass.entity;

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

// full_time_employee
// --------------------------------
// | id  | name        | salary   |
// --------------------------------
// | 2   | employee 2  | 10500    |
// --------------------------------

// part_time_employee
// -----------------------------------
// | id  | name        | hourly_wage |
// -----------------------------------
// | 1   | employee 1  | 65          |
// -----------------------------------

// SELECT e.id, e.name, e.hourly_wage, e.salary, e.clazz_ FROM (
//   SELECT id, name, hourly_wage, null as salary, 1 as clazz_ FROM part_time_employees
//   UNION ALL
//   SELECT id, name, null as hourly_wage, salary, 2 as clazz_ FROM full_time_employees
// ) e

// with TABLE_PER_CLASS strategy, each concrete subclass is provided with an individual table
// with TABLE_PER_CLASS strategy, the data is retrieved using a UNION ALL select
// Disadvantages:
// - the common columns are duplicated among each individual table: full_time_employee.name, part_time_employee.name (does not meet 3rd normal form)
// - each subclass has its own table
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class EmployeeTablePerClass {
  @Id
  @GeneratedValue
  private Long id;

  @Column(nullable = false)
  private String name;

  public EmployeeTablePerClass(String name) {
    this.name = name;
  }
}
