package uk.co.parkers.model.jpa;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = ValidSetValue.TABLE_NAME)
public class ValidSetValue implements Serializable {

	private static final long serialVersionUID = -5851229337809801554L;

	public static final String TABLE_NAME = "VALIDSETVALUES";

	@Id
	@Column(name = "KEY", unique = true)
	String key;

	@Column(name = "VALUE")
	@ElementCollection
	List<String> value;
	
	public ValidSetValue(){}

	public ValidSetValue(String key, List<String> value) {
		super();
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public List<String> getValue() {
		return value;
	}

	public void setValue(List<String> value) {
		this.value = value;
	}

}
