/*package com.eStore.app;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;

public class BackGroundActivity extends Activity {
	private SharedPreferences prefs;    
	private static final String SELECTED_ITEM = "SelectedItem"; 
	private Editor sharedPrefEditor;
	final Context context = this;
	*//**
	 * Called when the activity is first created.
	 *//*
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);


		btnchangeColor = (ImageButton) findViewById(R.id.btnchangeColor);
		btnchangeColor.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				final CharSequence[] items={getString(R.string.default),getString(R.string.pix1), getString(R.string.pix2))};
				AlertDialog.Builder builder = new AlertDialog.Builder(
						ContentView.this);

				builder.setTitle((getResources().getString(R.string.color_switch)));
				builder.setPositiveButton((R.string.ok), new DialogInterface.OnClickListener() { 

					@Override
					public void onClick(DialogInterface dialog, int which) {

					}
				});

				builder.setSingleChoiceItems(items, getSelectedItem(), new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {                
						wvContent = (WebView) findViewById(R.id.wvContent);             
						int bg_color=0;

						if(getString(R.string.default).equals(items[which]))
						{                   
							wvContent.setBackgroundColor(0);
							BitmapDrawable bg = (BitmapDrawable)getResources().getDrawable(R.drawable.default);
							bg.setTileModeXY(TileMode.REPEAT, TileMode.REPEAT);
							wvContent.setBackgroundDrawable(bg);                    
							bg_color=R.drawable.default; 
						}
						else if(getString(R.string.pix1).equals(items[which]))
						{
							wvContent.setBackgroundColor(0);
							BitmapDrawable bg = (BitmapDrawable)getResources().getDrawable(R.drawable.pix1);
							bg.setTileModeXY(TileMode.REPEAT, TileMode.REPEAT);
							wvContent.setBackgroundDrawable(bg);                    
							bg_color=R.drawable.pix1;
						}
						else if(getString(R.string.pix2).equals(items[which]))
						{
							wvContent.setBackgroundColor(0);
							BitmapDrawable bg = (BitmapDrawable)getResources().getDrawable(R.drawable.pix2);
							bg.setTileModeXY(TileMode.REPEAT, TileMode.REPEAT);
							wvContent.setBackgroundDrawable(bg);                    
							bg_color=R.drawable.pix2;                   
						}               
						saveSelectedItem(bg_color);
					}
				});
				builder.show();
				//OnCreate
				wvContent = (WebView) findViewById(R.id.wvContent); 
				wvContent.setBackgroundColor(getSelectedItem());

				private int getSelectedItem() {
					if (prefs == null) {
						prefs = PreferenceManager
								.getDefaultSharedPreferences(this);
					}
					return prefs.getInt(SELECTED_ITEM, -1);
				}

				private void saveSelectedItem(int which) {
					if (prefs == null) {
						prefs = PreferenceManager
								.getDefaultSharedPreferences(this);
					}
					sharedPrefEditor = prefs.edit();
					sharedPrefEditor.putInt(SELECTED_ITEM, which);
					sharedPrefEditor.commit();
				}
			});
		}}*/