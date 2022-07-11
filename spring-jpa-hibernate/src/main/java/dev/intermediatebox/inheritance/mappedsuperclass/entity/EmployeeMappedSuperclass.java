package dev.intermediatebox.inheritance.mappedsuperclass.entity;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

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

// SELECT e_ft.id, e_ft.name, e_ft.salary FROM full_time_employee e_ft
// SELECT e_pt.id, e_pt.name, e_pt.hourly_wage FROM part_time_employee e_ft

// with @MappedSuperclass, EmployeeMappedSuperclass is no longer an @Entity, which means that EmployeeMappedSuperclassRepository
// can only have JPQL statements that reference either PartTimeEmployeeMappedSuperclass or FullTimeEmployeeMappedSuperclass, but not EmployeeMappedSuperclass
// with @MappedSuperclass is like there is no relationship / inheritance hierarchy at all between Employee and FullTimeEmployee/PartTimeEmployee
// similar with TABLE_PER_CLASS
// Disadvantages:
// - the common columns are duplicated among each individual table: full_time_employee.name, part_time_employee.name (does not meet 3rd normal form)

@MappedSuperclass
public abstract class EmployeeMappedSuperclass {
  @Id
  @GeneratedValue
  private Long id;

  @Column(nullable = false)
  private String name;

  public EmployeeMappedSuperclass(String name) { this.name = name; }
}
