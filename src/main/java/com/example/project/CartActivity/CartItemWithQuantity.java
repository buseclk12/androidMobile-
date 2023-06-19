package com.example.project;

public class CartItemWithQuantity {
    private CartItem cartItem;
    private int quantity;

    public CartItemWithQuantity(CartItem cartItem, int quantity) {
        this.cartItem = cartItem;
        this.quantity = quantity;
    }

    public CartItem getCartItem() {
        return cartItem;
    }

    public int getQuantity() {
        return quantity;
    }
}
