package uk.co.parkers.domain.web;

import java.util.ArrayList;
import java.util.List;

public class Row {
	
	private String id;

	List<Field> fields = new ArrayList<>();
	
	public Row(){}

	public Row(String id, List<Field> fields) {
		super();
		this.id = id;
		this.fields = fields;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Field> getFields() {
		return fields;
	}

	public void setFields(List<Field> fields) {
		this.fields = fields;
	}
	
}
