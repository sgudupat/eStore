package com.eStore.app;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;


import com.client.vote.common.SimpleHttpClient;
import com.client.vote.domain.Address;


import android.app.Activity;
import android.app.ActivityGroup;
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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileAddressActivity extends Activity {
	 
	 final Context context = this;
	String username;
	String addressId;
	String name;
	String mobile;
	String email;
	String response = "";
	ArrayList<Address> items= new ArrayList<Address>();

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile_address);
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
		username = preferences.getString("username", "");		
		name = preferences.getString("username", "");
		mobile = preferences.getString("mobile", "");
		email = preferences.getString("email", "");
		
		
		
	        Log.i("username::", username);
	        ListView listView = (ListView) findViewById(R.id.address_list);
	        Log.i("inside build list", ""+listView);	  
	        // Adding items to list view
	        // 1. pass context and data to the custom adapter
	        Log.i("inside build list", "list built");	   
	        ArrayList<Address> items1=addressList(username);
	        Log.i("new list size",""+items1.size());
	        final AddressAdapter adapter = new AddressAdapter(addressList(username), ProfileAddressActivity.this);
	    
	        Log.i("inside build list", "After Process");
	      
	        listView.setAdapter(adapter);
	        Log.i("After Process", ""+listView);
	       
           
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
	private String getAddress(String username)
	{
		Intent intent= getIntent();
		Log.i("getAddress", "getAddress");
		final ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
        postParameters.add(new BasicNameValuePair("username", username));
        try {
            Log.i("ProfileAddressActivity", "try");
            String response = SimpleHttpClient.executeHttpPost("/getAddress", postParameters);
            Log.i("Response:", response);
            return response;
        } catch (Exception e) {
            String errorMsg = e.getMessage() + "";
            Log.e("AnchorSummaryActivity", errorMsg);
            return "fail";
        }
	}
	private ArrayList<Address> addressList(String username){
		 try {
			 Log.i("ProfileAddresslistActivity", "try");
		String address=getAddress(username);
		Log.i("address", address);
		items.clear();
		
		 JSONArray jsonArray = new JSONArray(address);
         for (int i = 0; i < jsonArray.length(); i++) {
             JSONObject jsonobject = jsonArray.getJSONObject(i);            
             String listAddress1=jsonobject.getString("address1");
             addressId=jsonobject.getString("address_id");
             Log.i("listAddress1", listAddress1);
             String listAddress2=jsonobject.getString("address2");
             Log.i("listAddress2", listAddress2);
             String listCity=jsonobject.getString("city");
             Log.i("listCity", listCity);
             String listCountry=jsonobject.getString("country");
             Log.i("listCountry", listCountry);
             String listState=jsonobject.getString("state");
             Log.i("listState", listState);
             String ListPincode=jsonobject.getString("pin_code"); 
             Log.i("ListPincode", ListPincode);
            items.add(new Address(listAddress1,listAddress2,listCity,listCountry,listState,ListPincode));
           
          
         }
     } catch (Exception e) {
    	 return null;
     }
		return items;
     
		
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
	
	public void replaceContentView(String id, Intent newIntent) {
        View view = ((ActivityGroup) context).getLocalActivityManager().startActivity(id, newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
        ((Activity) context).setContentView(view);
    }
	public void modify(View view){
		
	
		 
		 LayoutInflater li = LayoutInflater.from(context);
	        View addressView = li.inflate(R.layout.add_address, null);
	        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
	        // set prompts.xml to alert dialog builder
	        alertDialogBuilder.setView(addressView);
	     
	        
	  
	        Log.i("session name", addressId);
	      
	        final EditText modifyAddress1 = (EditText) addressView.findViewById(R.id.modify_address1);
	        final EditText modifyAddress2 =(EditText) addressView.findViewById(R.id.modify_address2);
	        final EditText modifyCity =(EditText) addressView.findViewById(R.id.modify_city);
	        final EditText modifyState=(EditText) addressView.findViewById(R.id.modify_state);
	        final  EditText modifyCountry=(EditText) addressView.findViewById(R.id.modify_country);
	        final EditText modifyPincode=(EditText) addressView.findViewById(R.id.modify_pincode);
	      /*   SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(this);
	        addressId=preferences.getString("addressId", "");
	        String address1=preferences.getString("address1", "");
	        String address2=preferences.getString("address2", "");
	        String city=preferences.getString("city", "");
	        String state=preferences.getString("state", "");
	        String pinCode=preferences.getString("pinCode", "");
	        String country=preferences.getString("country", "");
	        modifyAddress1.setText(address1);
	        modifyAddress2.setText(address2);
	        modifyCity.setText(city);
	        modifyState.setText(state);
	        modifyCountry.setText(country);
	        modifyPincode.setText(pinCode);*/
	        // set dialog message
	        alertDialogBuilder.setCancelable(false).setPositiveButton("Save",
	                new DialogInterface.OnClickListener() {
	                    public void onClick(DialogInterface dialog, int id) {
	                    	  final ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
	                    	  postParameters.add(new BasicNameValuePair("addressId", addressId));
	            			  postParameters.add(new BasicNameValuePair("address1",modifyAddress1.getText().toString()));
	            			  postParameters.add(new BasicNameValuePair("address2",modifyAddress2.getText().toString()));
	            			  postParameters.add(new BasicNameValuePair("city",modifyCity.getText().toString()));
	            			  postParameters.add(new BasicNameValuePair("state",modifyState.getText().toString()));
	            			  postParameters.add(new BasicNameValuePair("country",modifyCountry.getText().toString()));
	            			  postParameters.add(new BasicNameValuePair("pinCode",modifyPincode.getText().toString()));
	            			 
	            			  
	            			try {
	            				 response = SimpleHttpClient.executeHttpPost("/modifyAddress", postParameters);
	            				Log.i("response", response);
	            				
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
	}
	
	


