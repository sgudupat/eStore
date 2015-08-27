package com.eStore.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ProfileActivity extends Activity {
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile);
	}
	public void StoreActivity(View view)
	{
		Intent intent = new Intent(this, StoreActivity.class);
		startActivity(intent);
	}

}
