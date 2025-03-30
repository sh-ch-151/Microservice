package com.microservice.example.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Question")
public class Question
{
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Integer id;
	private String quesTitle;
	private String option1;
	private String option2;
	private String option3;
	private String option4;
	private String rightAns;
	private String difficultyLevel;
	private String category;
}
