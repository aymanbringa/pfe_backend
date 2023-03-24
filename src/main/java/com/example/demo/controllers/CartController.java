package com.example.demo.controllers;

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
import com.example.demo.services.CartService;
import com.example.demo.services.UserDetailsServiceImpl;
import com.example.demo.services.UserDetailsImpl;


@RestController
@RequestMapping("/api/auth")
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/cart")
    public ResponseEntity<?> addToCart(@RequestParam Long productId, @RequestParam int quantity,@RequestParam Long userId) {
        System.out.println("productId: " + userId);
        	System.out.println("ksjlhd1234");
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
    public ResponseEntity<Double> getTotal() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Long userId = Long.parseLong(auth.getName());
        double total = cartService.getTotal(userId);
        return ResponseEntity.ok().body(total);
    }

    @GetMapping("/cart/content")
    public ResponseEntity<Cart> getCart() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Long userId = Long.parseLong(auth.getName());
        Cart cart = cartService.getCartByUserId(userId);
        return ResponseEntity.ok().body(cart);
    }
}
