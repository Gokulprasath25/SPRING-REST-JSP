package spring.mvc.crud.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.mvc.crud.dto.Person;
import spring.mvc.crud.exception.RecordExistException;
import spring.mvc.crud.exception.RecordNotFoundException;
import spring.mvc.crud.repository.PersonRepository;

@Service
public class PersonService {

	@Autowired
	PersonRepository personRepo;

	public void addPerson(Person personDto) throws RecordExistException {

		Person person = personRepo.findPersonById(personDto.getId());
		if (person != null)
			throw new RecordExistException();

		personRepo.save(personDto);
	}

	public List<Person> getPersonList() throws RecordNotFoundException {
		List<Person> personList = personRepo.findAll();
		if (personList == null)
			throw new RecordNotFoundException();

		return personList;
	}

	public Person getPersonById(int id) throws RecordNotFoundException {

		Person personDto = personRepo.findPersonById(id);

		if (personDto == null)
			throw new RecordNotFoundException();

		return personDto;
	}

	public void updatePerson(Person personDto) throws RecordNotFoundException {

		Person dto = personRepo.findPersonById(personDto.getId());

		if (dto == null)
			throw new RecordNotFoundException();

		dto.setName(personDto.getName() != null ? personDto.getName() : dto.getName());
		dto.setAge(personDto.getAge() != null ? personDto.getAge() : dto.getAge());
		dto.setCity(personDto.getCity() != null ? personDto.getCity() : dto.getCity());

		personRepo.save(dto);

	}

	public void deletePerson(int id) throws RecordNotFoundException {
		Person dto = personRepo.findPersonById(id);

		if (dto == null)
			throw new RecordNotFoundException();

		personRepo.delete(dto);
	}

}
