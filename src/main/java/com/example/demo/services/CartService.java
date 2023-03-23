package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Cart;
import com.example.demo.models.CartItem;
import com.example.demo.models.Produit;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.ProduitRepository;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;
    
    @Autowired
    private ProduitRepository produitRepository;

    public Cart getCartByUserId(Long userId) {
        return cartRepository.findByUserId(userId).orElse(null);
    }

    public void addProductToCart(Long userId, Long productId, int quantity) {
        Cart cart = getCartByUserId(userId);
        if (cart == null) {
            cart = new Cart(userId);
        }
        Produit product = produitRepository.findById(productId).orElse(null);
        if (product != null) {
            CartItem cartItem = cart.findCartItemByProduitId(productId);
            if (cartItem != null) {
                cartItem.setQuantity(cartItem.getQuantity() + quantity);
            } else {
                cart.addCartItem(new CartItem(product, quantity));
            }
            cartRepository.save(cart);
        }
    }

    public void removeProductFromCart(Long userId, Long productId, int quantity) {
        Cart cart = getCartByUserId(userId);
        if (cart == null) {
            return;
        }
        CartItem cartItem = cart.findCartItemByProduitId(productId);
        if (cartItem != null) {
            if (cartItem.getQuantity() > quantity) {
                cartItem.setQuantity(cartItem.getQuantity() - quantity);
            } else {
                cart.removeCartItem(cartItem);
            }
            cartRepository.save(cart);
        }
    }

    public double getTotal(Long userId) {
        Cart cart = getCartByUserId(userId);
        if (cart == null) {
            return 0;
        }
        return cart.getTotal();
    }
}
