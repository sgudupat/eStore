package com.eStore.app;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.client.vote.common.SimpleHttpClient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class StoreActivity extends Activity{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.store);
		   try {
	            final ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
	          
	       
	            String response = SimpleHttpClient.executeHttpPost("/getPrdoucts", postParameters);
	            Log.i("Response:", response);
	            
	           
	        } catch (Exception e) {
	            Log.e("register", e.getMessage() + "");
	            Toast.makeText(getApplicationContext(), "Products not available !!!", Toast.LENGTH_LONG).show();
	        }
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
