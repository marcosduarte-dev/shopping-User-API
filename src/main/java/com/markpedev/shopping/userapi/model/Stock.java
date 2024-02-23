package com.markpedev.shopping.userapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Stock {
	private String name;
    private String icon;
    private float price;
    private boolean increased;
    
    public Stock(String name, String icon, float price) {
        this.name = name;
        this.icon = icon;
        this.price = price;
    }
}
