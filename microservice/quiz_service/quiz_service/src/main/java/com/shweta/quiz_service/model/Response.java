package com.shweta.quiz_service.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Response
{
	private Integer id;
	private String resp;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getResp() {
		return resp;
	}
	
	public void setResp(String resp) {
		this.resp = resp;
	}
	
	@Override
	public String toString() {
		return "Response{" +
			"id=" + id +
			", resp='" + resp + '\'' +
			'}';
	}
}
