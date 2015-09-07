package com.eStore.app;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;


import com.client.vote.domain.Address;


import android.app.Activity;
import android.app.ActivityGroup;
import android.content.Context;
import android.content.Intent;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
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
    private  final Context context;
   

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
      /*  edit.setOnClickListener(new AddressListener(
                list.get(position).getAddress1()
                , list.get(position).getAddress2()
                , list.get(position).getCity()
                , list.get(position).getCountry()
                , list.get(position).getState()
                ,list.get(position).getPinCode()));
      
        edit.setText((CharSequence) list);*/
        
       
       
       
       
        String text=list.get(position).getAddress1()+","+ list.get(position).getAddress2()+","+list.get(position).getCity()+","+list.get(position).getState()+","+list.get(position).getCountry()+","+list.get(position).getPinCode();
        Log.i("text", text);
        listItemText.setText(text);   
        
      
        return view;
		
		
        
      
       }	
   /* public class AddressListener implements View.OnClickListener {
        private String address1;
        private String address2;
        private String city;
        private String state;
        private String country;
        private String pinCode;

        public AddressListener(String address1, String address2, String city, String state, String country,String pinCode) {
            this.address1 = address1;
            this.address2 = address2;
            this.city = city;
            this.state = state;
            this.country = country;
            this.pinCode = pinCode;
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, ProfileAddressActivity.class);
            intent.putExtra("address1", address1);
            intent.putExtra("address2", address2);
            intent.putExtra("city", city);
            intent.putExtra("state", state);
            intent.putExtra("country", country);
            intent.putExtra("pinCode", pinCode);
            context.startActivity(intent);
        }*/

      

        public void replaceContentView(String id, Intent newIntent) {
            View view = ((ActivityGroup) context).getLocalActivityManager().startActivity(id, newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
            ((Activity) context).setContentView(view);
        }

		
		
	  
    //}
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}
}
