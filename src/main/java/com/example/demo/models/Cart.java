package com.example.demo.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> cartItems = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Cart() {}

    public Cart(List<CartItem> cartItems) {
        this.cartItems = cartItems;
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

    public Cart(Long id) {
        this.id = id;
        this.cartItems = new ArrayList<>();
    }

    public CartItem findCartItemByProduitId(Long produitId) {
        for (CartItem cartItem : cartItems) {
            if (cartItem.getProduct().getId().equals(produitId)) {
                return cartItem;
            }
        }
        return null;
    }
}
