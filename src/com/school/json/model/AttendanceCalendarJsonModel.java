package com.school.json.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AttendanceCalendarJsonModel 
{
	private String id;
	private String title;
	private String value;
	private String start;
	private String end;
	private String rendering;
	private String constraint;
	private String color;
	
	
	
	@JsonProperty(value="id")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@JsonProperty(value="title")
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@JsonProperty(value="start")
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	
	@JsonProperty(value="end")
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	
	@JsonProperty(value="rendering")
	public String getRendering() {
		return rendering;
	}
	public void setRendering(String rendering) {
		this.rendering = rendering;
	}
	
	@JsonProperty(value="constraint")
	public String getConstraint() {
		return constraint;
	}
	public void setConstraint(String constraint) {
		this.constraint = constraint;
	}
	
	@JsonProperty(value="color")
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
	@JsonProperty(value="value")
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	
	
}
