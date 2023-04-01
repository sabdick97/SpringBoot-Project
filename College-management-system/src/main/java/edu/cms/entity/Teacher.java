package edu.cms.entity;


	import java.util.List;

	import javax.persistence.Column;
	import javax.persistence.Entity;
	import javax.persistence.OneToMany;
	import javax.persistence.Table;
	import lombok.AllArgsConstructor;
	import lombok.Builder;
	import lombok.Getter;
	import lombok.NoArgsConstructor;
	import lombok.Setter;
	import lombok.ToString;

	@Entity
	@Table(name="teacher")
	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	@ToString
	public class Teacher extends User{

		@Column(name ="first_name", length = 20, nullable=false)
		private String firstName;
		@Column(name="last_name", length = 20, nullable=false)
		private String lastName;
		@Column(name="email", length = 50, nullable=false, unique = true)
		private String email;
		@Column( length = 10, nullable=false, unique=true)
		private String contact;
		
		@OneToMany
		private List<Course> course;
	}

