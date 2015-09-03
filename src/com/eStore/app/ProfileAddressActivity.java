package com.eStore.app;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import com.client.vote.common.SimpleHttpClient;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileAddressActivity extends Activity {
	String username;
	String name;
	String mobile;
	String email;
	String response = "";

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile_address);
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
		username = preferences.getString("username", "");
		name = preferences.getString("username", "");
		mobile = preferences.getString("mobile", "");
		email = preferences.getString("email", "");

		TextView addressName = (TextView) findViewById(R.id.addressprofile_name);
		TextView addressMobile = (TextView) findViewById(R.id.addressprofile_mobile);
		TextView addressEmail = (TextView) findViewById(R.id.addressprofile_Email);
		final ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
		  postParameters.add(new BasicNameValuePair("username", username));
		try {
			response = SimpleHttpClient.executeHttpPost("/getUser", postParameters);
			JSONObject jsonObject = new JSONObject(response);
			name = jsonObject.getString("user_name");
			mobile = jsonObject.getString("mobile");
			email = jsonObject.getString("email");
			addressName.setText(name);
			addressMobile.setText(mobile);
			addressEmail.setText(email);

		} catch (Exception e) {
			Log.i("Response 2:Error:", e.getMessage());
            Toast.makeText(getApplicationContext(), "Update Failed, Please Retry !!!", Toast.LENGTH_LONG).show();
		}
	}
	public void addAddress(View view){
		final Context context= this;
		 LayoutInflater li = LayoutInflater.from(context);
	        View addressView = li.inflate(R.layout.add_address, null);
	        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
	        // set prompts.xml to alert dialog builder
	        alertDialogBuilder.setView(addressView);
	        /*SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(this);
	        username=preferences.getString("username", "");
	        String address1=preferences.getString("address1", "");
	        String address2=preferences.getString("address2", "");
	        String city=preferences.getString("city", "");
	        String state=preferences.getString("state", "");
	        String pinCode=preferences.getString("pinCode", "");
	        String country=preferences.getString("country", "");*/
	        final EditText modifyAddress1 = (EditText) addressView.findViewById(R.id.modify_address1);
	        final EditText modifyAddress2 =(EditText) addressView.findViewById(R.id.modify_address2);
	        final EditText modifyCity =(EditText) addressView.findViewById(R.id.modify_city);
	        final EditText modifyState=(EditText) addressView.findViewById(R.id.modify_state);
	        final  EditText modifyCountry=(EditText) addressView.findViewById(R.id.modify_country);
	        final EditText modifyPincode=(EditText) addressView.findViewById(R.id.modify_pincode);
	       /* modifyAddress1.setText(address1);
	        modifyAddress2.setText(address2);
	        modifyCity.setText(city);
	        modifyState.setText(state);
	        modifyCountry.setText(country);
	        modifyPincode.setText(pinCode);*/
	        // set dialog message
	        alertDialogBuilder.setCancelable(false).setPositiveButton("OK",
	                new DialogInterface.OnClickListener() {
	                    public void onClick(DialogInterface dialog, int id) {
	                    	  final ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
	            			  postParameters.add(new BasicNameValuePair("username", username));
	            			  postParameters.add(new BasicNameValuePair("address1",modifyAddress1.getText().toString()));
	            			  postParameters.add(new BasicNameValuePair("address2",modifyAddress2.getText().toString()));
	            			  postParameters.add(new BasicNameValuePair("city",modifyCity.getText().toString()));
	            			  postParameters.add(new BasicNameValuePair("state",modifyState.getText().toString()));
	            			  postParameters.add(new BasicNameValuePair("country",modifyCountry.getText().toString()));
	            			  postParameters.add(new BasicNameValuePair("pinCode",modifyPincode.getText().toString()));
	            			  
	            			  
	            			try {
	            				response = SimpleHttpClient.executeHttpPost("/addAddress", postParameters);
	            				 SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);

	            	                SharedPreferences.Editor editor = prefs.edit();

	            	                editor.putString("address1", modifyAddress1.getText().toString());
	            	                editor.putString("address2", modifyAddress2.getText().toString());
	            	                editor.putString("city", modifyCity.getText().toString());
	            	                editor.putString("state", modifyState.getText().toString());
	            	                editor.putString("country", modifyCountry.getText().toString());
	            	                editor.putString("pinCode", modifyPincode.getText().toString());
	            	                
	            	                editor.commit();
	            	                

	            			} catch (Exception e) {
	            				Log.i("Response 2:Error:", e.getMessage());
	            	            Toast.makeText(getApplicationContext(), "Update Failed, Please Retry !!!", Toast.LENGTH_LONG).show();
	            			}
	            		
	                    	
	                        // get user input and set it to result edit text
	                        //String aotp = userInput.getText().toString();
	                        //Log.i("alert dialog otp", aotp);
	                      
	                    }
	                    
	                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog, int id) {
	                dialog.cancel();
	            }
	        });
	        // create alert dialog
	        AlertDialog alertDialog = alertDialogBuilder.create();
	        // show it
	        alertDialog.show();
	    }
/*	public void saveAddress(View view){
		final Context context =this;
		 SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(this);
	        username=preferences.getString("username", "");
	        String address1=preferences.getString("address1", "");
	        String address2=preferences.getString("address2", "");
	        String city=preferences.getString("city", "");
	        String state=preferences.getString("state", "");
	        String pinCode=preferences.getString("pinCode", "");
	        String country=preferences.getString("country", "");
	         EditText modifyAddress1 = (EditText) findViewById(R.id.modify_address1);
	        EditText modifyAddress2 =(EditText) findViewById(R.id.modify_address2);
	        EditText modifyCity =(EditText) findViewById(R.id.modify_city);
	        EditText modifyState=(EditText) findViewById(R.id.modify_state);
	        EditText modifyCountry=(EditText) findViewById(R.id.modify_country);
	        EditText modifyPincode=(EditText) findViewById(R.id.modify_pincode);
	        modifyAddress1.setText(address1);
	        modifyAddress2.setText(address2);
	        modifyCity.setText(city);
	        modifyState.setText(state);
	        modifyCountry.setText(country);
	        modifyPincode.setText(pinCode);
	        Log.i("address1", modifyAddress1.getText().toString());
	        Log.i("address2", modifyAddress2.getText().toString());
	        Log.i("city", modifyCity.getText().toString());
	        Log.i("state", modifyState.getText().toString());
	        Log.i("country", modifyCountry.getText().toString());
	        Log.i("pinCode", modifyPincode.getText().toString());
	        final ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
			  postParameters.add(new BasicNameValuePair("username", username));
			  postParameters.add(new BasicNameValuePair("address1",modifyAddress1.getText().toString()));
			  postParameters.add(new BasicNameValuePair("address2",modifyAddress2.getText().toString()));
			  postParameters.add(new BasicNameValuePair("city",modifyCity.getText().toString()));
			  postParameters.add(new BasicNameValuePair("state",modifyState.getText().toString()));
			  postParameters.add(new BasicNameValuePair("country",modifyCountry.getText().toString()));
			  postParameters.add(new BasicNameValuePair("pinCode",modifyPincode.getText().toString()));
			  
			  
			try {
				response = SimpleHttpClient.executeHttpPost("/addAddress", postParameters);
				 SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);

	                SharedPreferences.Editor editor = prefs.edit();

	                editor.putString("address1", modifyAddress1.getText().toString());
	                editor.putString("address2", modifyAddress2.getText().toString());
	                editor.putString("city", modifyCity.getText().toString());
	                editor.putString("state", modifyState.getText().toString());
	                editor.putString("country", modifyCountry.getText().toString());
	                editor.putString("pinCode", modifyPincode.getText().toString());
	                
	                editor.commit();
	                Intent intent = new Intent(this, ProfileAddressActivity.class);
	                startActivity(intent);

			} catch (Exception e) {
				Log.i("Response 2:Error:", e.getMessage());
	            Toast.makeText(getApplicationContext(), "Update Failed, Please Retry !!!", Toast.LENGTH_LONG).show();
			}*/
		
	}//
	
	


