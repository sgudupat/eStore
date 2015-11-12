package com.eStore.app;

import java.util.ArrayList;

import com.eStore.domain.Product;
import com.eStore.domain.ShoppingCart;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class ProductDetailActivity extends Activity {
	Product product=new Product();
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.product_detail);
		Intent intent = getIntent();
		Bitmap bitmap = (Bitmap) intent.getParcelableExtra("bitmapImage");
		String name=intent.getStringExtra("name");
		String info=intent.getStringExtra("specification");
		String price=intent.getStringExtra("price");
		 TextView textprice = (TextView) findViewById(R.id.pd_productprice);
		 TextView textinfo = (TextView) findViewById(R.id.pd_productinfo);
		 TextView textname = (TextView) findViewById(R.id.pd_productname);
		 textprice.setText(price);
		 textinfo.setText(info);
		 textname.setText(name);
		 product.setName(name);
		 product.setPrice(price);
		 product.setSpecification(info);
		 Spinner ageView = (Spinner) findViewById(R.id.product_values);
	        // Create an ArrayAdapter using the string array and a default spinner layout
	        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.values_array, android.R.layout.simple_spinner_item);
	        // Specify the layout to use when the list of choices appears
	        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	        // Apply the adapter to the spinner
	        ageView.setAdapter(adapter);
	        //ageView.setSelection(adapter.getPosition(age));
		
		 
		ImageView view = (ImageView)findViewById(R.id.pd_productimage);
		view.setImageBitmap(bitmap);
		
	}
	public void PaymentPage(View view){
		Intent intent = new Intent(this,PaymentActivity.class);
		startActivity(intent);
	}
	public void addtoCart(View view){
//		Intent intent = new Intent(this,PaymentActivity.class);
//		startActivity(intent);
		ArrayList<Product> cart=ShoppingCart.getCart();
		cart.add(product);
		
		
	}
	public void DestoreProfile(View view){
		Intent intent = new Intent(this,ProfileActivity.class);
		startActivity(intent);
	}

}
