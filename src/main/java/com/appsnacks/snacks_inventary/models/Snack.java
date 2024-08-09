package com.appsnacks.snacks_inventary.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="snacks")
public class Snack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name="name_product", nullable=false, length = 30)
    private String product;
    
    @Column(name="price", nullable = false, length = 20)
    private double price;
    
    @Column(name="expiration_date", nullable = false)
    private String expirationDate;
    
    @Column(name="path_image")
    private String pathImg;

    public Snack() {
    }

    public Snack(int id, String product, double price, String expirationDate, String pathImg) {
        this.id = id;
        this.product = product;
        this.price = price;
        this.expirationDate = expirationDate;
        this.pathImg = pathImg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }
    
    public String getPathImg(){
        return pathImg;
    }
    public void setPathImg(String pathImg){
        this.pathImg = pathImg;
    }

    @Override
    public String toString() {
        return "Snack{" + "id=" + id + ", product=" + product + ", price=" + price + ", expirationDate=" + expirationDate + '}';
    }
    
    
}
