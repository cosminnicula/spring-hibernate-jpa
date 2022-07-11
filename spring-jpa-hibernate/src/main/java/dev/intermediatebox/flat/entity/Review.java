package dev.intermediatebox.flat.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Entity

@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Review {
	@Id
	@GeneratedValue
	private Long id;

	private String description;

	@Enumerated(EnumType.STRING)
	private ReviewRating rating;

	// @ToString.Exclude and @EqualsAndHashCode.Exclude prevent infinite recursion. Nothing to do with Hibernate, just Lombok
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	// @JsonIgnore prevent infinite recursion. Nothing to do with Hibernate, just Jackson (Spring Data JPA REST)
	@JsonIgnore
	// @ManyToOne has by default EAGER fetch type
	// As a general rule: *ToOne has default EAGER fetch type, while *ToMany has default LAZY fetch type
	// many reviews are associated with one course (the review table has a course_id column)
	// Review is the owning side of the relationship, so the mappedBy annotation should be on Course
	@ManyToOne
	private Course course;

	public Review(ReviewRating rating, String description) {
		this.rating = rating;
		this.description = description;
	}
}