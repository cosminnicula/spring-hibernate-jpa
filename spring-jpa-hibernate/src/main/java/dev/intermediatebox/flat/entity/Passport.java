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
public class Passport {
	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String number;

	// @ToString.Exclude and @EqualsAndHashCode.Exclude prevent infinite recursion. Nothing to do with Hibernate, just Lombok
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	// @JsonIgnore prevent infinite recursion. Nothing to do with Hibernate, just Jackson (Spring Data JPA REST)
	@JsonIgnore
	// mappedBy prevents adding a new column student_id to passport table
	// Having @OneToOne on both Passport and Student, the relationship is bidirectional
	// mappedBy declares the owning side of the relationship => Student is the owning side of the relationship in this case, because Passport.student is annotated with mappedBy = "passport"
	// without mappedBy, Hibernate would have been created two unidirectional (foreign key) relations on the database, to implement one bidirectional object relation; what we want is only a single foreign key on table student towards passport
	// mappedBy is the name of the Java property that represents a passport
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "passport")
	private Student student;

	public Passport(String number) {
		this.number = number;
	}
}