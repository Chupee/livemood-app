package com.example.livemood.adapters;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.livemood.R;
import com.example.livemood.views.LMTextView;

public class TagsListAdapter extends BaseAdapter {
	private List<String> tagsList;
	LayoutInflater inflater;
	
	public TagsListAdapter(Context context, List<String> list){
		this.tagsList = list;
		this.inflater = LayoutInflater.from(context);
	}
	
	public int getCount(){
		return tagsList.size();
	}
	
	public Object getItem(int position) {
		return tagsList.get(position);
	}
	
	public long getItemId(int position) {
		return position;
	}

	private class ViewHolder {
		public TextView tagName;
	}

	public void clear() {
		tagsList.clear();
	}
	
	public void setList(List<String> list) {
		tagsList = list;
	}
	
	public View getView(int position, View concertView, ViewGroup parent) {
		ViewHolder holder;

		if (concertView == null) {
			holder = new ViewHolder();

			concertView = inflater.inflate(R.layout.tags_list_item, null);
			holder.tagName = (LMTextView) concertView.findViewById(R.id.tagListItem);

			
			concertView.setTag(holder);
		} else {
			holder = (ViewHolder) concertView.getTag();
		}

		/* Data */
		Log.i("TAGSLIST", tagsList.toString());
		Log.i("POSITION", Integer.toString(position));
		holder.tagName.setText(tagsList.get(position));

		return concertView;
	}
}
