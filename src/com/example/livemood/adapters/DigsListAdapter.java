package com.example.livemood.adapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.livemood.R;
import com.example.livemood.models.Dig;
import com.example.livemood.views.LMTextView;

public class DigsListAdapter extends BaseAdapter {

	private List<Dig> digsList;
	LayoutInflater inflater;
	
	public DigsListAdapter(Context context, List<Dig> list){
		this.digsList = list;
		this.inflater = LayoutInflater.from(context);
	}
	
	public int getCount(){
		return digsList.size();
	}
	
	public Object getItem(int position) {
		return digsList.get(position);
	}
	
	public long getItemId(int position) {
		return position;
	}

	private class ViewHolder {
		public LMTextView diggerName;
		public LMTextView digContent;
	}

	public void clear() {
		digsList.clear();
	}
	
	public void setList(List<Dig> list) {
		digsList = list;
	}
	
	public View getView(int position, View concertView, ViewGroup parent) {
		ViewHolder holder;

		if (concertView == null) {
			holder = new ViewHolder();

			concertView = inflater.inflate(R.layout.digs_list_item, null);
			holder.diggerName = (LMTextView) concertView.findViewById(R.id.diggerName);
			holder.digContent = (LMTextView) concertView.findViewById(R.id.digContent);
			
			concertView.setTag(holder);
		} else {
			holder = (ViewHolder) concertView.getTag();
		}

		holder.diggerName.setText(digsList.get(position).getDigger().getUser().getLogin());
		holder.digContent.setText(digsList.get(position).getContent());
		

		return concertView;
	}

}
