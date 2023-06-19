package com.example.project;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CartAdapter cartAdapter;
    private List<CartItem> cartItems = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        // Sepet öğelerini al
        List<CartItemWithQuantity> cartItemsWithQuantity = convertToCartItemWithQuantity(cartItems);


        // RecyclerView ve adaptörü tanımla
        recyclerView = findViewById(R.id.cart_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        cartAdapter = new CartAdapter(cartItemsWithQuantity, this);
        recyclerView.setAdapter(cartAdapter);

        // Sepeti onayla ve alışverişi tamamla
        findViewById(R.id.checkout_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Burada sepeti işleyebilir ve ödeme işlemlerini gerçekleştirebilirsiniz

                // Alışverişi tamamladıktan sonra sepeti temizle
                CartManager.getInstance(CartActivity.this).clearCart();
                cartAdapter.notifyDataSetChanged();
            }
        });
    }

    private List<CartItemWithQuantity> convertToCartItemWithQuantity(List<CartItem> cartItems) {
        List<CartItemWithQuantity> cartItemsWithQuantity = new ArrayList<>();
        for (CartItem cartItem : cartItems) {
            CartItemWithQuantity itemWithQuantity = new CartItemWithQuantity(cartItem, 1);
            cartItemsWithQuantity.add(itemWithQuantity);
        }
        return cartItemsWithQuantity;
    }

    List<CartItemWithQuantity> cartItemsWithQuantity = convertToCartItemWithQuantity(cartItems);


}