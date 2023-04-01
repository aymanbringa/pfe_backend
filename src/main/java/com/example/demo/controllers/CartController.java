package com.example.demo.controllers;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Cart;
import com.example.demo.models.CartItem;
import com.example.demo.services.CartService;
import com.example.demo.services.UserDetailsImpl;

@RestController
@RequestMapping("/api/auth")
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/cart")
    public ResponseEntity<?> addToCart(@RequestParam Long productId, @RequestParam int quantity, @RequestParam Long userId) {
        cartService.addProductToCart(userId, productId, quantity);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/cart/delete")
    public ResponseEntity<?> removeFromCart(@RequestParam Long productId, @RequestParam int quantity) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        Long userId = ((UserDetailsImpl) userDetailsService.loadUserByUsername(userDetails.getUsername())).getId();
        cartService.removeProductFromCart(userId, productId, quantity);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/cart")
    public ResponseEntity<List<CartItem>> getCart(@RequestParam Long userId) {
        try {
            Cart cart = cartService.getCartByUserId(userId);
            if (cart == null) {
                return ResponseEntity.notFound().build();
            }
            List<CartItem> cartItems = new ArrayList<>();
            for (CartItem cartItem : cart.getCartItems()) {
                cartItems.add(cartItem);
            }
            return ResponseEntity.ok().body(cartItems);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/cart/content")
    public double totale(@RequestParam Long userId)
    {	System.out.println("jksgjdgjsdgfjshqgf");
    	return cartService.getTotal(userId);
    }
    @GetMapping("/cart/items")
    public ResponseEntity<List<CartItem>> getCartItemsByCartId(@RequestParam Long cartId) {
        try {
            Optional<Cart> optionalCart = cartService.getCartById(cartId);
            if (optionalCart.isPresent()) {
                Cart cart = optionalCart.get();
                List<CartItem> cartItems = cartService.getCartItemsByCart(cart);
                return ResponseEntity.ok().body(cartItems);
            } else {
                throw new RuntimeException("Cart not found for id :: " + cartId);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }







}
