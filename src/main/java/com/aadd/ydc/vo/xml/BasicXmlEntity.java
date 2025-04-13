package com.aadd.ydc.vo.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

//@JasonIgnoreProperties(ignoreUnknown = true)  esto para que no lea la clase 
@JacksonXmlRootElement(localName = "BasicXmlEntity")
public class BasicXmlEntity {

	@JacksonXmlProperty(localName = "id")
	private Long id;
	@JacksonXmlProperty(localName = "name")
	private String name;

	// Getters y Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}