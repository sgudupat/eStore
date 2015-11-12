package com.eStore.domain;

import java.util.ArrayList;

public class ShoppingCart {
	 private static ArrayList<Product> cart;
	 public static ArrayList<Product> getCart() {
		  if(cart == null) {
		   cart = new ArrayList<Product>();
		  }
		   
		  return cart;
		 }

}
