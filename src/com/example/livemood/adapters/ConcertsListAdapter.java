package com.example.livemood.adapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.livemood.R;
import com.example.livemood.models.Concert;

public class ConcertsListAdapter extends BaseAdapter {

	private List<Concert> concertsList;
	LayoutInflater inflater;
	
	public ConcertsListAdapter(Context context, List<Concert> list){
		this.concertsList = list;
		this.inflater = LayoutInflater.from(context);
	}
	
	public int getCount(){
		return concertsList.size();
	}
	
	public Object getItem(int position) {
		return concertsList.get(position);
	}
	
	public long getItemId(int position) {
		return position;
	}

	private class ViewHolder {
		public TextView name;
	}

	public void clear() {
		concertsList.clear();
	}
	
	public void setList(List<Concert> list) {
		concertsList = list;
	}
	
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;

		if (convertView == null) {
			holder = new ViewHolder();

			convertView = inflater.inflate(R.layout.concerts_list, null);
			holder.name = (TextView) convertView.findViewById(R.id.concertName);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		/* Address name */
		holder.name.setText(concertsList.get(position).getArtist().getName());
		
		return convertView;
	}

}
