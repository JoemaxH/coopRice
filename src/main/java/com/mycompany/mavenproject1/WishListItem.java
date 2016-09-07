/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

/**
 *
 * @author Max
 */
public class WishListItem {
    
    private int id;
    private String title = "";
    private String genre = "";
    private int localp;
    private int onlinep;
    //sharkcheap
    private String normalPrice;
    private String salePrice;
    private String savings;
    private int metacriticScore;
    
    public WishListItem(String title, String genre, int localp, int onlinep,
            String normalPrice, String salePrice, String savings, int metacriticScore){
        
        this.title = title;
        this.genre = genre;
        this.localp = localp;
        this.onlinep = onlinep;
        this.normalPrice = normalPrice;
        this.salePrice = salePrice;
        this.savings = savings;
        this.metacriticScore = metacriticScore;
    }
    public WishListItem(){
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getLocalp() {
        return localp;
    }

    public void setLocalp(int localp) {
        this.localp = localp;
    }

    public int getOnlinep() {
        return onlinep;
    }

    public void setOnlinep(int onlinep) {
        this.onlinep = onlinep;
    }

    public String getNormalPrice() {
        return normalPrice;
    }

    public void setNormalPrice(String normalPrice) {
        this.normalPrice = normalPrice;
    }

    public String getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(String salePrice) {
        this.salePrice = salePrice;
    }

    public String getSavings() {
        return savings;
    }

    public void setSavings(String savings) {
        this.savings = savings;
    }

    public int getMetacriticScore() {
        return metacriticScore;
    }

    public void setMetacriticScore(int metacriticScore) {
        this.metacriticScore = metacriticScore;
    }
    
    
}
