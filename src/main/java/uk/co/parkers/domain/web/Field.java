package uk.co.parkers.domain.web;

public class Field {
	
	private String name;
	
	private String label;
	
	private String value;
	
	public Field(){}

	public Field(String name, String label, String value) {
		super();
		this.name = name;
		this.label = label;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	

}
