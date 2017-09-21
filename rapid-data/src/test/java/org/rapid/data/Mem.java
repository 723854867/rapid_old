package org.rapid.data;

import org.rapid.util.common.model.UniqueModel;

public class Mem implements UniqueModel<Integer> {

	private static final long serialVersionUID = 9014317126181330937L;

	private int id;
	private String name;
	private int age;
	private int created;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	public int getCreated() {
		return created;
	}
	
	public void setCreated(int created) {
		this.created = created;
	}

	@Override
	public Integer key() {
		return this.id;
	}
}
