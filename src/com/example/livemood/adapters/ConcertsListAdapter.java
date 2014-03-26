package com.example.livemood.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.livemood.R;
import com.example.livemood.models.Concert;
import com.nostra13.universalimageloader.core.ImageLoader;

public class ConcertsListAdapter extends BaseAdapter {

	private ArrayList<Concert> concertsList;
	private Context context;
	
	LayoutInflater inflater;
	
	public ConcertsListAdapter(Context context, ArrayList<Concert> concertsList2){
		super();
		this.concertsList = concertsList2;
		this.inflater = LayoutInflater.from(context);
		this.context = context;
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
	
	public void setList(ArrayList<Concert> list) {
		concertsList = list;
	}
	
	public View getView(int position, View concertView, ViewGroup parent) {
		ViewHolder holder = null;
		
		Concert current = concertsList.get(position);

		if (concertView == null) {
			holder = new ViewHolder();
		} else {
			holder = (ViewHolder) concertView.getTag();
		}
		
		concertView = inflater.inflate(R.layout.concerts_list_item, null);
		holder.artistName = (TextView) concertView.findViewById(R.id.concertName);
		holder.labelName = (TextView) concertView.findViewById(R.id.labelName);
		holder.placeName = (TextView) concertView.findViewById(R.id.placeName);
		holder.date = (TextView) concertView.findViewById(R.id.date);
		holder.reviewCount = (TextView) concertView.findViewById(R.id.reviewCount);
		holder.hasBook = (TextView) concertView.findViewById(R.id.hasBook);
		holder.artistImage = (ImageView) concertView.findViewById(R.id.artistImage);
		
		holder.artistImage.setImageResource(R.drawable.concert1);
		
		ImageLoader imageLoader = ImageLoader.getInstance();
		imageLoader.displayImage(current.getImage(), holder.artistImage);
		holder.hasBookIcon = (ImageView) concertView.findViewById(R.id.hasBookIcon);
		
		concertView.setTag(holder);
		Log.i("VIEW == NULL",  "CONCERTS LIST ADAPTER");
		
		holder.artistName.setText(current.getArtist().getName());
		holder.labelName.setText(current.getArtist().getLabel().getName());
		holder.placeName.setText(current.getPlace().getName());
		holder.date.setText(current.getDate());
		holder.reviewCount.setText("15 chroniques");
		boolean hasBook = true ; // TODO : get the real state of this field by asking User data
		if(hasBook) {
			holder.hasBook.setText(R.string.label_book_yes);
			holder.hasBookIcon.setImageResource(R.drawable.check);
		}
		

		return concertView;
	}

}
