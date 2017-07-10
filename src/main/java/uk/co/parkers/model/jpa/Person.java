package uk.co.parkers.model.jpa;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import uk.co.parkers.validation.annotation.ValidSetValueConstraintValidator;

@Entity
@Table(name = Person.TABLE_NAME)
public class Person {

	public static final String TABLE_NAME = "PERSON";

	public enum Sex {
		MALE, FEMALE
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long pk;

	@Column(name = "NAME")
	@Size(min = 4, max = 8)
	String name;

	@Column(name = "SEX")
	@Enumerated(EnumType.STRING)
	Sex sex;

	@Column(name = "DOB")
	LocalDateTime dob;

	@Column(name = "INTCOL")
	int intColumn;

	@Column(name = "DBLCOL")
	double dblColumn;

	@Column(name = "NEW_COLUMN")
	@ValidSetValueConstraintValidator(key="columns")
	String newColumn;

	@ManyToMany(mappedBy = "persons", cascade = CascadeType.MERGE)
	List<Child> children = new ArrayList<>();

	public Person() {
	}

	public Person(String name, Sex sex, LocalDateTime dob) {
		super();
		this.name = name;
		this.sex = sex;
		this.dob = dob;
	}

	public Long getPk() {
		return pk;
	}

	public void setPk(Long pk) {
		this.pk = pk;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public LocalDateTime getDob() {
		return dob;
	}

	public void setDob(LocalDateTime dob) {
		this.dob = dob;
	}

	public int getIntColumn() {
		return intColumn;
	}

	public void setIntColumn(int intColumn) {
		this.intColumn = intColumn;
	}

	public double getDblColumn() {
		return dblColumn;
	}

	public void setDblColumn(double dblColumn) {
		this.dblColumn = dblColumn;
	}

	public String getNewColumn() {
		return newColumn;
	}

	public void setNewColumn(String newColumn) {
		this.newColumn = newColumn;
	}

	public List<Child> getChildren() {
		return children;
	}

	public void setChildren(List<Child> children) {
		this.children = children;
	}

}
