package com.cosmos.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "usercart001")
public class UserCart {
	@Id
	private Long mobileNumber;
	@OneToMany(mappedBy = "cart" , cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JsonManagedReference
	private Set<Item> items;
	
	public UserCart() {
		super();
	}
	
	public Long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(Long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public Set<Item> getItems() {
		return items;
	}

	public void setItems(Set<Item> items) {
		this.items = items;
	}

	
	
}
