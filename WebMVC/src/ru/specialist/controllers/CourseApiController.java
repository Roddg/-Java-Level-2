package ru.specialist.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ru.specialist.DAO.CourseService;
import ru.specialist.DAO.Course;

@Controller
@RequestMapping("api/course")
public class CourseApiController {
	
	private CourseService courseService;
	@Autowired
	public void setContactService(CourseService courseService) {
		this.courseService= courseService;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<Course> list() {
		return courseService.findAll();
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Course findById(@PathVariable int id) {
		return courseService.findById(id);
	}
	
	@RequestMapping(value="/", method = RequestMethod.POST)
	@ResponseBody
	public Course create(@RequestBody Course course) {
		return courseService.save(course);
	}
	@RequestMapping(value="/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Course update(@RequestBody Course course, @PathVariable int id) {
		return courseService.save(course);
	}
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void delete(@PathVariable int id) {
		courseService.delete(id);
	}

}
