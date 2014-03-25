package com.example.livemood.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.livemood.R;

public class DrawerListAdapter extends BaseAdapter {

	private ArrayList<String> itemList;
	private ArrayList<String> iconList;
	private Context context;
	
	LayoutInflater inflater;
	
	public DrawerListAdapter(Context context, ArrayList<String> itemList, ArrayList<String> iconList){
		this.itemList = itemList;
		this.iconList = iconList;
		this.inflater = LayoutInflater.from(context);
		this.context = context;
	}
	
	public int getCount(){
		return itemList.size();
	}
	
	public Object getItem(int position) {
		return itemList.get(position);
	}
	
	public long getItemId(int position) {
		return position;
	}

	private class ViewHolder {
		public TextView itemName;
		public ImageView itemIcon;
	}

	public void clear() {
		itemList.clear();
	}
	
	public void setList(ArrayList<String> list) {
		itemList = list;
	}
	
	public View getView(int position, View view, ViewGroup parent) {
		ViewHolder holder = null;

		if (view == null) {
			holder = new ViewHolder();

			view = inflater.inflate(R.layout.drawer_list_item, null);
			holder.itemName = (TextView) view.findViewById(R.id.itemName);
			holder.itemIcon = (ImageView) view.findViewById(R.id.itemIcon);

			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}

		/* Address name */
		String currentItem = itemList.get(position);
		String currentIcon = iconList.get(position);
		holder.itemName.setText(currentItem);
		int iconId = context.getResources().getIdentifier(currentIcon, "drawable", context.getPackageName());
		holder.itemIcon.setImageResource(iconId);

		return view;
	}

}
