package com.eStore.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class StoreActivity extends Activity{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.store);
	}
	public void EstoreProfile(View view){
		Intent intent = new Intent(this,ProfileActivity.class);
		startActivity(intent);
	}
	public void ProductDetails (View view){
		Intent intent = new Intent(this,ProductDetailActivity.class);
		startActivity(intent);
	}

}
