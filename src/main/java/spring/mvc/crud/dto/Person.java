package spring.mvc.crud.dto;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;



@Data
@Entity
@Table(name = "jsp.Person")
public class Person {

	@Id
	private Integer id;

	@Column
	private String name;

	@Column
	private Integer age;

	@Column
	private String city;

}
