package ru.specialist.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.context.MessageSource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.validation.BindingResult;
import javax.servlet.http.HttpServletRequest;

import ru.specialist.DAO.CourseService;
import ru.specialist.DAO.Course;

@Controller
@RequestMapping("/courses/")
public class CourseController {
	private final Logger logger = LoggerFactory.getLogger(CourseController.class);
	
	private CourseService courseService;
	private MessageSource messageSource;
	@Autowired
	public void setContactService(CourseService courseService) {
		this.courseService= courseService;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model uiModel) {
		logger.info("Listing courses");
		List<Course> courses = courseService.findAll();
		uiModel.addAttribute ("courses", courses);
		logger.info("No. of courses: " + courses.size());
		return "courses/list";
	}
	
	//@PreAuthorize("isAuthenticated()")
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "delete/{id}",method = RequestMethod.GET)
	public String delete(@PathVariable("id") int id, Model uiModel) {
		if (courseService.findById(id)!=null)
			courseService.delete(id);
		logger.info("Delete course: " + id);
		return "redirect:/courses/";
	}
	@RequestMapping(value = "update/{id}",method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") int id, Model uiModel) {
		uiModel.addAttribute ("course", courseService.findById(id));
		return "courses/edit";
	}
	@RequestMapping(value = "update/0",method = RequestMethod.GET)
	public String newForm( Model uiModel) {
		return "courses/edit";
	}
	
	//@PreAuthorize("isAuthenticated()")
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "update/{id}", method = RequestMethod.POST)
	public String update(Course course, BindingResult bindingResult,
				Model uiModel, HttpServletRequest httpServletRequest,
				RedirectAttributes redirectAttributes) {
		logger.info("Updating contact");
		if (bindingResult.hasErrors()) {
			uiModel.addAttribute("course", course);
			return "courses/update";
		}
		courseService.save(course);
		return "redirect:/courses/";
	}

}
