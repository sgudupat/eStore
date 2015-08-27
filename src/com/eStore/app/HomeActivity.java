package com.eStore.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    public void signin(View view){
    	Intent intent= new Intent(this, StoreActivity.class);
    	startActivity(intent);
    }
    public void EstoreRegister(View view){
    	Intent intent = new Intent(this, RegisterActivity.class);
    	startActivity(intent);
    }
    public void EstoreForgotPassword(View view){
    	Intent intent =new Intent(this, ForgotPasswordActivity.class);
    	startActivity(intent);
    }
   
}
