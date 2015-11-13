package com.client.vote.domain;

import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class Cart {
	String name;
	String price;
	String quantity;
	ImageView image;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public ImageView getImage() {
		return image;
	}
	public void setImage(ImageView image) {
		this.image = image;
	}
	@Override
	public String toString() {
		return "Cart [name=" + name + ", price=" + price + ", quantity="
				+ quantity + ", image=" + image + "]";
	}


}
