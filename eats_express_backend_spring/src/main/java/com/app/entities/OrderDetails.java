package com.app.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderDetails{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "cust_id", nullable = false)
	private User user;
	
	
	@OneToMany(fetch = FetchType.EAGER)
	@JsonIgnore
	private List<Product> product=new ArrayList<Product>();
	
	@OneToOne
	private Payment payment;
	
	@Column
	private Double amount;
	
	
//	private List<Product> productList = new ArrayList<Product>();
//	public void addProduct(Product product) {
//		productList.add(product);
//	}
	

}