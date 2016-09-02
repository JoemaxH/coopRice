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
    
    private String title = "";
    private String genre = "";
    private String local = "";
    private String online = "";
    //sharkcheap
    private String normalPrice = "";
    private String salePrice = "";
    private String savings = "";
    private String metacriticScore = "";
    
    public WishListItem(String title, String genre, String local, String online,
            String normalPrice, String salePrice, String savings, String metacriticScore){
        this.title = title;
        this.genre = genre;
        this.local = local;
        this.online = online;
        this.normalPrice = normalPrice;
        this.salePrice = salePrice;
        this.savings = savings;
        this.metacriticScore = metacriticScore;
    }
    public WishListItem(){
        
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

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getOnline() {
        return online;
    }

    public void setOnline(String online) {
        this.online = online;
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

    public String getMetacriticScore() {
        return metacriticScore;
    }

    public void setMetacriticScore(String metacriticScore) {
        this.metacriticScore = metacriticScore;
    }
    
    
}
