package dev.intermediatebox.inheritance.joined.entity;

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
// ---------------------
// | id  | name        |
// ---------------------
// | 1   | employee 1  |
// ---------------------

// full_time_employee
// ------------------
// | id  | salary   |
// ------------------
// | 2   | 10500    |
// ------------------

// part_time_employee
// ---------------------
// | id  | hourly_wage |
// ---------------------
// | 1   | 65          |
// ---------------------

// SELECT e.id, e.name, e_pt.hourly_wage, e_ft.salary, CASE WHEN e_pt.id IS NOT NULL THEN 1 WHEN e_ft.id IS NOT NULL THEN 2 WHEN e.id IS NOT NULL THEN 0 END AS clazz_
// FROM employee e
// LEFT OUTER JOIN e_pt ON e.id = e_pt.id
// LEFT OUTER JOIN e_ft ON e.id = e_ft.id

// with JOINED strategy, the fields specific to each concrete subclass are mapped to individual tables; the common fields are in a separate table
// with JOINED strategy, the data is retrieved using JOIN select
// with JOINED there is no duplication of columns
// Disadvantages:
// - performance, because of the JOINs
// Advantages:
// - data integrity, data quality

@Inheritance(strategy = InheritanceType.JOINED)
public abstract class EmployeeJoined {
  @Id
  @GeneratedValue
  private Long id;

  @Column(nullable = false)
  private String name;

  public EmployeeJoined(String name) {
    this.name = name;
  }
}
