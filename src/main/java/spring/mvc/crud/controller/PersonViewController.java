package spring.mvc.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;
import spring.mvc.crud.dao.PersonService;
import spring.mvc.crud.dto.Person;
import spring.mvc.crud.exception.RecordNotFoundException;
import spring.mvc.crud.utils.HelperUtils;

@Slf4j
@Controller
@RequestMapping("/person")
public class PersonViewController {

	@Autowired
	PersonService service;

	@GetMapping("/home")
	@ResponseBody
	public String home(@RequestParam("name") String name) {
		return "Hello " + name + " !";
	}

	@GetMapping("/getPerson")
	public String getPerson(@RequestParam("id") int id, Model model) {
		try {
			Person person = service.getPersonById(id);
			log.info("Response dto --> " + person);
			model.addAttribute("person", person);
		} catch (RecordNotFoundException e) {
			log.error(e.getMessage() + " for Id - " + id);
			model.addAttribute("error", "Record not found for ID: " + id);
		} catch (Exception e) {
			log.error("An unexpected error occurred for ID {}: {}", id, e.getMessage());
			model.addAttribute("error", "An unexpected error occurred. Please try again later.");
		}
		return "personInfo";
	}

	@GetMapping("/getPersonList")
	public String personList(Model model) {
		try {
			List<Person> list = service.getPersonList();
			log.info("Response dto --> " + list);
			if (list.isEmpty()) {
				model.addAttribute("message", "No persons found.");
				log.info("No persons found in the database.");
			} else {
				model.addAttribute("person", list);
			}
		} catch (RecordNotFoundException e) {
			log.error("Record not found: {}", e.getMessage());
			model.addAttribute("error", "Record not found.");
		} catch (Exception e) {
			log.error("An unexpected error occurred: {}", e.getMessage());
			model.addAttribute("error", "An unexpected error occurred. Please try again later.");
		}
		return "personList";
	}

}
