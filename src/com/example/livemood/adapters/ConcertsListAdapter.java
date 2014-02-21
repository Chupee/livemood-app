package com.example.livemood.adapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.livemood.R;
import com.example.livemood.models.Concert;
import com.example.livemood.views.LMTextView;

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
		public TextView artistName;
		public TextView labelName;
		public TextView placeName;
		public TextView date;
		public TextView reviewCount;
		public TextView hasBook;
		public ImageView artistImage;
		public ImageView hasBookIcon;
	}

	public void clear() {
		concertsList.clear();
	}
	
	public void setList(List<Concert> list) {
		concertsList = list;
	}
	
	public View getView(int position, View concertView, ViewGroup parent) {
		ViewHolder holder;

		if (concertView == null) {
			holder = new ViewHolder();

			concertView = inflater.inflate(R.layout.concerts_list_item, null);
			holder.artistName = (LMTextView) concertView.findViewById(R.id.concertName);
			holder.labelName = (LMTextView) concertView.findViewById(R.id.labelName);
			holder.placeName = (LMTextView) concertView.findViewById(R.id.placeName);
			holder.date = (LMTextView) concertView.findViewById(R.id.date);
			holder.reviewCount = (LMTextView) concertView.findViewById(R.id.reviewCount);
			holder.hasBook = (LMTextView) concertView.findViewById(R.id.hasBook);
			holder.artistImage = (ImageView) concertView.findViewById(R.id.artistImage);
			holder.hasBookIcon = (ImageView) concertView.findViewById(R.id.hasBookIcon);
			
			concertView.setTag(holder);
		} else {
			holder = (ViewHolder) concertView.getTag();
		}

		/* Address name */
		holder.artistImage.setImageResource(R.drawable.concert1);
		holder.artistName.setText(concertsList.get(position).getArtist().getName());
		holder.labelName.setText(concertsList.get(position).getArtist().getLabel().getName());
		holder.placeName.setText(concertsList.get(position).getPlace().getName());
		holder.date.setText(concertsList.get(position).getDate());
		holder.reviewCount.setText("15 chroniques");
		boolean hasBook = true ; // TODO : get the real state of this field by asking User data
		if(hasBook) {
			holder.hasBook.setText(R.string.label_book_yes);
			holder.hasBookIcon.setImageResource(R.drawable.check);
		}
		

		return concertView;
	}

}
