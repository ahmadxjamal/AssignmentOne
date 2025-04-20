package com.example.assigntmentone.PCShop;

public class PC {
    private String company;
    private String model;
    private String category;
    private int price;
    private int quantity;


    public PC() {
    }

    public PC(String company, String model, String category, int price, int quantity) {
        this.company = company;
        this.model = model;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return company + " model:" + model + " category=" + category + "\nprice=" + price + " quantity=" + quantity;
    }
}
