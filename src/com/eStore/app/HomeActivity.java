package com.eStore.app;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.IntentSender.SendIntentException;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.client.vote.common.SimpleHttpClient;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;


public class HomeActivity extends Activity implements OnClickListener,
ConnectionCallbacks, OnConnectionFailedListener {
	 private static final int RC_SIGN_IN = 0;
	    // Logcat tag
	    private static final String TAG = "MainActivity";
	 
	    // Profile pic image size in pixels
	    private static final int PROFILE_PIC_SIZE = 400;
	 
	    // Google client to interact with Google API
	    private GoogleApiClient mGoogleApiClient;
	 
	    /**
	     * A flag indicating that a PendingIntent is in progress and prevents us
	     * from starting further intents.
	     */
	    private boolean mIntentInProgress;
	 
	    private boolean mSignInClicked;
	 
	    private ConnectionResult mConnectionResult;
	 
	    private SignInButton btnSignIn;
	    private Button btnSignOut, btnRevokeAccess;
	    private ImageView imgProfilePic;
	    private TextView txtName, txtEmail;
	    private LinearLayout llProfileLayout;
	 final Context context = this;
	 private Timer myTimer;
	 int images[];
	LinearLayout linearLayout;
	public static int count=0;
	int[] drawablearray=new int[]{R.drawable.bg,R.drawable.image6,R.drawable.image1,R.drawable.image2,R.drawable.image3,R.drawable.image4,R.drawable.image5,R.drawable.image16,R.drawable.image7,R.drawable.image8,R.drawable.image9,R.drawable.image10,R.drawable.image11,R.drawable.image12,R.drawable.image13,R.drawable.image14};
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    // final  LinearLayout container = (LinearLayout) findViewById(R.id.estore_relative);
       // linLay.setBackgroundColor(int color);
       // int images[] = {R.drawable.bg, R.drawable.bgs, R.drawable.image1};
        
        
     new Handler().postDelayed(new Runnable() {
         public void run() {

            if(count<drawablearray.length){

            	HomeActivity.this.getWindow().
                    setBackgroundDrawableResource(drawablearray[count]);
                count++;  //<<< increment counter here
             }
            else{
               // reset counter here
               count=0;
             }

           }
       }, 0);
     btnSignIn = (SignInButton) findViewById(R.id.btn_sign_in);
     btnSignOut = (Button) findViewById(R.id.btn_sign_out);
     btnRevokeAccess = (Button) findViewById(R.id.btn_revoke_access);
     imgProfilePic = (ImageView) findViewById(R.id.imgProfilePic);
     txtName = (TextView) findViewById(R.id.txtName);
     txtEmail = (TextView) findViewById(R.id.txtEmail);
     llProfileLayout = (LinearLayout) findViewById(R.id.llProfile);

     // Button click listeners
     btnSignIn.setOnClickListener(this);
     btnSignOut.setOnClickListener(this);
     btnRevokeAccess.setOnClickListener(this);

     mGoogleApiClient = new GoogleApiClient.Builder(this)
             .addConnectionCallbacks(this)
             .addOnConnectionFailedListener(this).addApi(Plus.API)
             .addScope(Plus.SCOPE_PLUS_LOGIN).build();
 }

 protected void onStart() {
     super.onStart();
     mGoogleApiClient.connect();
 }

 protected void onStop() {
     super.onStop();
     if (mGoogleApiClient.isConnected()) {
         mGoogleApiClient.disconnect();
     }
 }

 /**
  * Method to resolve any signin errors
  * */
 private void resolveSignInError() {
     if (mConnectionResult.hasResolution()) {
         try {
             mIntentInProgress = true;
             mConnectionResult.startResolutionForResult(this, RC_SIGN_IN);
         } catch (SendIntentException e) {
             mIntentInProgress = false;
             mGoogleApiClient.connect();
         }
     }
 }

 @Override
 public void onConnectionFailed(ConnectionResult result) {
     if (!result.hasResolution()) {
         GooglePlayServicesUtil.getErrorDialog(result.getErrorCode(), this,
                 0).show();
         return;
     }

     if (!mIntentInProgress) {
         // Store the ConnectionResult for later usage
         mConnectionResult = result;

         if (mSignInClicked) {
             // The user has already clicked 'sign-in' so we attempt to
             // resolve all
             // errors until the user is signed in, or they cancel.
             resolveSignInError();
         }
     }

 }

 @Override
 protected void onActivityResult(int requestCode, int responseCode,
         Intent intent) {
     if (requestCode == RC_SIGN_IN) {
         if (responseCode != RESULT_OK) {
             mSignInClicked = false;
         }

         mIntentInProgress = false;

         if (!mGoogleApiClient.isConnecting()) {
             mGoogleApiClient.connect();
         }
     }
 }

 @Override
 public void onConnected(Bundle arg0) {
     mSignInClicked = false;
     Toast.makeText(this, "User is connected!", Toast.LENGTH_LONG).show();

     // Get user's information
     getProfileInformation();

     // Update the UI after signin
     updateUI(true);

 }
 

 /**
  * Updating the UI, showing/hiding buttons and profile layout
  * */
 private void updateUI(boolean isSignedIn) {
     if (isSignedIn) {
         btnSignIn.setVisibility(View.GONE);
         btnSignOut.setVisibility(View.VISIBLE);
         btnRevokeAccess.setVisibility(View.VISIBLE);
         llProfileLayout.setVisibility(View.VISIBLE);
     } else {
         btnSignIn.setVisibility(View.VISIBLE);
         btnSignOut.setVisibility(View.GONE);
         btnRevokeAccess.setVisibility(View.GONE);
         llProfileLayout.setVisibility(View.GONE);
     }
 }

 /**
  * Fetching user's information name, email, profile pic
  * */
 private void getProfileInformation() {
     try {
         if (Plus.PeopleApi.getCurrentPerson(mGoogleApiClient) != null) {
             Person currentPerson = Plus.PeopleApi
                     .getCurrentPerson(mGoogleApiClient);
             String personName = currentPerson.getDisplayName();
             String personPhotoUrl = currentPerson.getImage().getUrl();
             String personGooglePlusProfile = currentPerson.getUrl();
             String email = Plus.AccountApi.getAccountName(mGoogleApiClient);

             Log.e(TAG, "Name: " + personName + ", plusProfile: "
                     + personGooglePlusProfile + ", email: " + email
                     + ", Image: " + personPhotoUrl);

             txtName.setText(personName);
             txtEmail.setText(email);

             // by default the profile url gives 50x50 px image only
             // we can replace the value with whatever dimension we want by
             // replacing sz=X
             personPhotoUrl = personPhotoUrl.substring(0,
                     personPhotoUrl.length() - 2)
                     + PROFILE_PIC_SIZE;

             new LoadProfileImage(imgProfilePic).execute(personPhotoUrl);

         } else {
             Toast.makeText(getApplicationContext(),
                     "Person information is null", Toast.LENGTH_LONG).show();
         }
     } catch (Exception e) {
         e.printStackTrace();
     }
 }

 @Override
 public void onConnectionSuspended(int arg0) {
     mGoogleApiClient.connect();
     updateUI(false);
 }



 /**
  * Button on click listener
  * */
 @Override
 public void onClick(View v) {
     switch (v.getId()) {
     case R.id.btn_sign_in:
         // Signin button clicked
         signInWithGplus();
         break;
     case R.id.btn_sign_out:
         // Signout button clicked
         signOutFromGplus();
         break;
     case R.id.btn_revoke_access:
         // Revoke access button clicked
         revokeGplusAccess();
         break;
     }
 }

 /**
  * Sign-in into google
  * */
 private void signInWithGplus() {
     if (!mGoogleApiClient.isConnecting()) {
         mSignInClicked = true;
         resolveSignInError();
     }
 }

 /**
  * Sign-out from google
  * */
 private void signOutFromGplus() {
     if (mGoogleApiClient.isConnected()) {
         Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);
         mGoogleApiClient.disconnect();
         mGoogleApiClient.connect();
         updateUI(false);
     }
 }

 /**
  * Revoking access from google
  * */
 private void revokeGplusAccess() {
     if (mGoogleApiClient.isConnected()) {
         Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);
         Plus.AccountApi.revokeAccessAndDisconnect(mGoogleApiClient)
                 .setResultCallback(new ResultCallback<Status>() {
                     @Override
                     public void onResult(Status arg0) {
                         Log.e(TAG, "User access revoked!");
                         mGoogleApiClient.connect();
                         updateUI(false);
                     }

                 });
     }
 }

 /**
  * Background Async task to load user profile picture from url
  * */
 private class LoadProfileImage extends AsyncTask<String, Void, Bitmap> {
     ImageView bmImage;

     public LoadProfileImage(ImageView bmImage) {
         this.bmImage = bmImage;
     }

     protected Bitmap doInBackground(String... urls) {
         String urldisplay = urls[0];
         Bitmap mIcon11 = null;
         try {
             InputStream in = new java.net.URL(urldisplay).openStream();
             mIcon11 = BitmapFactory.decodeStream(in);
         } catch (Exception e) {
             Log.e("Error", e.getMessage());
             e.printStackTrace();
         }
         return mIcon11;
     }

     protected void onPostExecute(Bitmap result) {
         bmImage.setImageBitmap(result);
     }
 }
     

 
/*private int getRandomNumber() {
	Log.i("getRandomNumber", "getRandomNumber");
    //Note that general syntax is Random().nextInt(n)
    //It results in range 0-4
    //So it should be equal to number of images in images[] array
    return new Random().nextInt(3);
}*/

    public void signin(View view){
    	final EditText userName = (EditText) findViewById(R.id.signin_name);
	
	 	final EditText password = (EditText) findViewById(R.id.signin_password);
	 	 try {
             final ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
             postParameters.add(new BasicNameValuePair("username", userName.getText().toString()));
             postParameters.add(new BasicNameValuePair("password", password.getText().toString()));
             String response = SimpleHttpClient.executeHttpPost("/loginUser", postParameters);
             Log.i("Response:", response);
            JSONObject jsonobject = new JSONObject(response);
             SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
             SharedPreferences.Editor editor = prefs.edit();
             Log.i("username", (String) jsonobject.get("user_name"));
             editor.putString("username", (String) jsonobject.get("user_name"));
             editor.putString("email", (String) jsonobject.get("email"));
             editor.putString("mobile", (String) jsonobject.get("mobile"));
             editor.commit();
             Intent intent= new Intent(this, StoreActivity.class);
    	startActivity(intent);
         } catch (Exception e) {
             Log.e("register", e.getMessage() + "");
             Toast.makeText(getApplicationContext(), "Login Failed, Please Retry !!!", Toast.LENGTH_LONG).show();
         }
    	
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