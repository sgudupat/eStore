package com.eStore.app;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.client.vote.domain.Cart;

public class CartAdapter extends BaseAdapter implements ListAdapter {
private ArrayList<Cart> cart=new ArrayList<Cart>();
private Context context;
public CartAdapter (ArrayList<Cart> cart ,Context context){
	this.cart=cart;
	this.context=context;
}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		if (view == null) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view=inflater.inflate(R.layout.cart_item, null);
			
		}
		TextView productName=(TextView) view.findViewById(R.id.cartproduct_name);
		productName.setText(cart.get(position).getName());
		TextView productPrice=(TextView) view.findViewById(R.id.cartproduct_price);
		productPrice.setText(cart.get(position).getPrice());
		ImageView productImage=(ImageView) view.findViewById(R.id.cartproduct_image);
		productImage.setBackground(cart.get(position).getImage());
		return view;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}
}