package com.eStore.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class RegisterActivity extends Activity{
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
	}
	public void EstoreHomePage(View view){
		Intent intent = new Intent(this,HomeActivity.class);
		startActivity(intent);
	}

}
