package com.eStore.app;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;


import com.client.vote.domain.Address;

import android.app.Activity;
import android.app.ActivityGroup;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;


public class AddressAdapter extends BaseAdapter implements ListAdapter {
	
    private ArrayList<Address> list = new ArrayList<Address>();
    private Context context;

    public AddressAdapter(ArrayList<Address> list, Context context) {
        this.list = list;
        this.context = context;
    }
   

    public int getCount() {
    	Log.i("list size",""+list.size());
        return list.size();
    }

    public Object getItem(int pos) {
        return list.get(pos);
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
        	 Log.i("address list size",""+list.size());
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.address_item, null);
           
            
        }
        Log.i("text", "insideAdapter");
        //Handle TextView and display string from your address activity
        TextView listItemText = (TextView) view.findViewById(R.id.address_info);  
        Button delete=(Button) view.findViewById(R.id.address_delete);
        Button edit=(Button) view.findViewById(R.id.address_edit);
        Log.i("delete", ""+delete);
       
       
       
       
        String text=list.get(position).getAddress1()+","+ list.get(position).getAddress2()+","+list.get(position).getCity()+","+list.get(position).getState()+","+list.get(position).getCountry()+","+list.get(position).getPinCode();
        Log.i("text", text);
        listItemText.setText(text);   
        
      
        return view;
		
		
        
      
       }	

      

        public void replaceContentView(String id, Intent newIntent) {
            View view = ((ActivityGroup) context).getLocalActivityManager().startActivity(id, newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
            ((Activity) context).setContentView(view);
        }

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}
	  
    }

