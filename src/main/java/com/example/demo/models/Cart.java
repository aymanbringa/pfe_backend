package com.example.demo.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> cartItems = new ArrayList<>();
    
    @OneToMany(mappedBy = "cart", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Commande> commandes = new ArrayList<>();


    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties("carts")

    private User user;

    public Cart() {}

    public Cart(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    /**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        user.getCarts().add(this); // add this cart to the user's list of carts
    }

    public void addCartItem(CartItem cartItem) {
        if (cartItems == null) {
            cartItems = new ArrayList<>();
        }
        cartItems.add(cartItem);
        cartItem.setCart(this);
    }

    public void removeCartItem(CartItem cartItem) {
        if (cartItems != null) {
            cartItems.remove(cartItem);
            cartItem.setCart(null);
        }
    }

    public double getTotal() {
        double total = 0.0;
        for (CartItem cartItem : cartItems) {
            total += cartItem.getSubTotal();
        }
        return total;
    }

    public CartItem findCartItemByProduitId(Long produitId) {
        for (CartItem cartItem : cartItems) {
            if (cartItem.getProduct().getId().equals(produitId)) {
                return cartItem;
            }
        }
        return null;
    }

	@Override
	public String toString() {
		return "Cart [id=" + id + ", cartItems=" + cartItems + ", user=" + user + "]";
	}
    
}
