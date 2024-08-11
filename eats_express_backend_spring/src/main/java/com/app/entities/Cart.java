package com.app.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "carts")
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cartId;

//	@OneToOne
//	@JoinColumn(name = "user_id")
//	private User user;
//	
//	@OneToMany(mappedBy = "cart", cascade = {CascadeType.PERSIST,CascadeType.MERGE}, orphanRemoval = true)
//	private List<CartItem> cartItems = new ArrayList<CartItem>();

	@OneToMany /*
				 * ( mappedBy = "cart", cascade = {CascadeType.PERSIST,CascadeType.MERGE},
				 * orphanRemoval = true)
				 */
	private List<Product> products;

	private Double totalPrice = 0.0;

}
