package ru.specialist.web.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the lessons database table.
 * 
 */
@Entity
@Table(name="lessons")
@NamedQuery(name="Lesson.findAll", query="SELECT l FROM Lesson l")
public class Lesson implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private int course;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="lesson_date")
	private Date lessonDate;

	private String room;

	private int teacher;

	public Lesson() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCourse() {
		return this.course;
	}

	public void setCourse(int course) {
		this.course = course;
	}

	public Date getLessonDate() {
		return this.lessonDate;
	}

	public void setLessonDate(Date lessonDate) {
		this.lessonDate = lessonDate;
	}

	public String getRoom() {
		return this.room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public int getTeacher() {
		return this.teacher;
	}

	public void setTeacher(int teacher) {
		this.teacher = teacher;
	}

}