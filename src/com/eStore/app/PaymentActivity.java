package com.eStore.app;

import java.util.ArrayList;

import com.eStore.domain.Product;
import com.eStore.domain.ShoppingCart;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class PaymentActivity extends Activity{
	 private ArrayList<Product> mCartList;
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.payment);
		mCartList=ShoppingCart.getCart();
		Log.i("",""+mCartList.size());
		for(Product p: mCartList){
			Log.i("product name",p.getName());
			
		}
		
	}
	public void OrderSummary(View view)
	{
		Intent intent = new Intent (this,OrderSummaryActivity.class);
		startActivity(intent);
	}
	public void PestoreProfile(View view){
		Intent intent = new Intent(this,ProfileActivity.class);
		startActivity(intent);
	}

}
