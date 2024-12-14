package spring.mvc.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.mvc.crud.dto.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {

	public Person findPersonById(int id);

}
