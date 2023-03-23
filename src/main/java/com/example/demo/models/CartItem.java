package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Produit product;

    @ManyToOne
    private Cart cart;

    private int quantity;

    public CartItem(Produit product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public CartItem() {}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the product
	 */
	public Produit getProduct() {
		return product;
	}

	/**
	 * @param product the product to set
	 */
	public void setProduct(Produit product) {
		this.product = product;
	}

	/**
	 * @return the cart
	 */
	public Cart getCart() {
		return cart;
	}

	/**
	 * @param cart the cart to set
	 */
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	public double getSubTotal() {
	    return this.quantity * this.product.getPrix();
	}


	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "CartItem [id=" + id + ", product=" + product + ", cart=" + cart + ", quantity=" + quantity + "]";
	}

	/**
	 * @param product
	 * @param cart
	 * @param quantity
	 */
	public CartItem(Produit product, Cart cart, int quantity) {
		this.product = product;
		this.cart = cart;
		this.quantity = quantity;
	}
    
    
}
