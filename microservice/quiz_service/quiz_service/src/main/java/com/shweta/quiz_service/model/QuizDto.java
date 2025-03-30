package com.shweta.quiz_service.model;


public class QuizDto
{
	private String category;
	private int noOfQue;
	private String title;
	
	public String getCategory() {
		return category;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	public int getNoOfQue() {
		return noOfQue;
	}
	
	public void setNoOfQue(int noOfQue) {
		this.noOfQue = noOfQue;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
}
