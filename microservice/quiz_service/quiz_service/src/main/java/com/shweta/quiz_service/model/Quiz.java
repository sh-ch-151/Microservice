package com.shweta.quiz_service.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Quiz
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String title;
	@ElementCollection
	private List<Integer> quesIds;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public List<Integer> getQuesIds() {
		return quesIds;
	}
	
	public void setQuesIds(List<Integer> quesIds) {
		this.quesIds = quesIds;
	}
	
	@Override
	public String toString() {
		return "Quiz{" +
			"id=" + id +
			", title='" + title + '\'' +
			", quesIds=" + quesIds +
			'}';
	}
}
