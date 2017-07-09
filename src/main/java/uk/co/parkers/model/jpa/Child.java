package uk.co.parkers.model.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = Child.TABLE_NAME)
public class Child {

	public static final String TABLE_NAME = "CHILD";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long pk;

	@Column(name = "NAME")
	String name;

	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name = "PERSON_CHILD", joinColumns = {
			@JoinColumn(name = "child_pk", referencedColumnName = "pk", insertable = true, updatable = true) }, inverseJoinColumns = {
					@JoinColumn(name = "parent_pk", referencedColumnName = "pk", insertable = true, updatable = true) })
	List<Person> persons = new ArrayList<>();

	public Child() {
	}

	public Child(String name) {
		super();
		this.name = name;
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

	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

}
