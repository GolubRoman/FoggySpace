package com.golubroman.golub.foggyspace.Shop.Fragments.Shop;

/**
 * Created by roman on 11.07.17.
 */

public class ShopElement {

    private String name, photoUrl, description, id;
    private int price;

    public ShopElement(){}

    public ShopElement(String name, String photoUrl, String description, int price, String id){
        this.name = name;
        this.photoUrl = photoUrl;
        this.description = description;
        this.price = price;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
