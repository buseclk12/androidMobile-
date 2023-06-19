package com.example.project;

public class CartItem {
    private String product;
    private String price;
    private String category;
    private String imageUrl;

    public CartItem(String product, String price, String category, String imageUrl) {
        this.product = product;
        this.price = price;
        this.category = category;
        this.imageUrl = imageUrl;
    }

    public String getProduct() {
        return product;
    }

    public String getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
