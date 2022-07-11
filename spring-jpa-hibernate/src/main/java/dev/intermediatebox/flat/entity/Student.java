package dev.intermediatebox.flat.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity

@Slf4j
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Student {
	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String name;

	private Address address;

	// adds a new column passport_id to student table; it also adds a FK constraint referencing passport table
	// FetchType.LAZY will prevent eager fetching of passport data, when retrieving a student
	// If Passport doesn't have a @OneToOne annotation as well, the relationship is unidirectional
	@OneToOne(fetch = FetchType.LAZY)
	private Passport passport;

	// @ToString.Exclude and @EqualsAndHashCode.Exclude prevent infinite recursion. Nothing to do with Hibernate, just Lombok
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	// @JsonIgnore prevent infinite recursion. Nothing to do with Hibernate, just Jackson (Spring Data JPA REST)
	@JsonIgnore
	@Setter(AccessLevel.NONE) // Don't generate setter for course, as courses will be added (addCourse) or removed (removeCourse)
	@ManyToMany
	// @JoinTable specifies that the joining table should have student_id and course_id (instead of the default students_id and courses_id)
	@JoinTable(name = "student_course", joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "course_id"))
	private List<Course> courses = new ArrayList<>();

	public Student(String name) {
		this.name = name;
	}

	public void addCourse(Course course) {
		this.courses.add(course);
	}

	public void removeCourse(Course course) {
		this.courses.remove(course);
	}
}