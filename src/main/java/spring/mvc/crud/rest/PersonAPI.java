package spring.mvc.crud.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import spring.mvc.crud.dao.PersonService;
import spring.mvc.crud.dto.Person;
import spring.mvc.crud.exception.RecordExistException;
import spring.mvc.crud.exception.RecordNotFoundException;
import spring.mvc.crud.utils.HelperUtils;

@Slf4j
@RestController
@RequestMapping("/personAPI")
public class PersonAPI {

	@Autowired
	PersonService service;

	@RequestMapping(value = "/addPerson", method = { RequestMethod.POST })
	public ResponseEntity<String> addPerson(@RequestBody Person personDto) {
		try {
			log.info("Request Received to Process addPerson ---> " + personDto);
			if (personDto.getId() == null || personDto.getName() == null)
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Name or Id cannot be null ");
			service.addPerson(personDto);
			log.info("Person Added Suceessfully !!!");
		} catch (RecordExistException e) {
			log.error(e.getMessage() + " - " + personDto.getId());
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body("Unable To add, " + e.getMessage() + " - " + personDto.getId());
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Sorry, we are unable to process your request. Please try after sometime.");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body("Person Added Suceessfully !!!");
	}

	@RequestMapping(value = "/getPerson", method = { RequestMethod.POST })
	public ResponseEntity<String> addPerson(@RequestParam("id") int id) {
		Person personDto = null;
		try {
			log.info("Request Received to Process getPerson for id --> " + id);
			personDto = service.getPersonById(id);
			log.info("Response dto ---> " + personDto);
		} catch (RecordNotFoundException e) {
			log.error(e.getMessage() + " for id - " + id);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Sorry, we are unable to process your request. Please try after sometime.");
		}
		return ResponseEntity.status(HttpStatus.OK).body(HelperUtils.convertDtoToJsonString(personDto));
	}

	@RequestMapping(value = "/getPersonList", method = { RequestMethod.POST })
	public ResponseEntity<String> getPersonList() {
		List<Person> personList = null;
		try {
			log.info("Request Received to Process get all Person list for id");
			personList = service.getPersonList();
			log.info("Response dto ---> " + personList);
		} catch (RecordNotFoundException e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Sorry, we are unable to process your request. Please try after sometime.");
		}
		return ResponseEntity.status(HttpStatus.OK).body(HelperUtils.convertDtoToJsonString(personList));
	}

	@RequestMapping(value = "/updatePersondetails", method = { RequestMethod.POST })
	public ResponseEntity<String> updatePerson(@RequestBody Person personDto) {

		try {
			log.info("Request Received to Process updatePerson --> " + personDto);
			if (personDto.getId() == null)
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id cannot be null ");
			service.updatePerson(personDto);
			log.info("Person updated Suceessfully !!!");
		} catch (RecordNotFoundException e) {
			log.error(e.getMessage() + " for id - " + personDto.getId());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Sorry, we are unable to process your request. Please try after sometime.");
		}
		return ResponseEntity.status(HttpStatus.OK).body("Person Upated Suceessfully !!!");

	}

	@RequestMapping(value = "/deletePerson", method = { RequestMethod.DELETE })
	public ResponseEntity<String> deletePerson(@RequestParam int id) {
		try {
			log.info("Request Received to Process deletePerson for id --> " + id);
			service.deletePerson(id);
			log.info("Person deleted Suceessfully !!!");
		} catch (RecordNotFoundException e) {
			log.error(e.getMessage() + " for id - " + id);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Sorry, we are unable to process your request. Please try after sometime.");
		}
		return ResponseEntity.status(HttpStatus.OK).body("Person Deleted Suceessfully !!!");
	}

}
