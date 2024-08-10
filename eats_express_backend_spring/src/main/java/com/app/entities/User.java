package com.app.entities;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.app.dto.AddressDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	
	@Column(length =20, nullable = false)
	private String firstName;
	
	@Column(length =20, nullable = false)
	private String lastName;
	
	@Column(length =50, nullable = false)
	private String email;
	
	@Column(length =50, nullable = false)
	private String password;
	
	@Column(length =10, nullable = false)
	private Long mobileNumber;
	
	@Enumerated(EnumType.STRING)
	private RoleEnum role;
	
	@OneToOne(cascade = {CascadeType.REMOVE, CascadeType.PERSIST},orphanRemoval = true)
	@JoinColumn(name = "address_id")
	private Address address;
	
	@JsonIgnore
	@OneToOne(mappedBy = "user", cascade = {CascadeType.REMOVE, CascadeType.PERSIST,CascadeType.MERGE}, orphanRemoval = true)
	private Cart cart;



}