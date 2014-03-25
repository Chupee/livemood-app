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
import com.example.livemood.models.Artist;
import com.nostra13.universalimageloader.core.ImageLoader;

public class ArtistsListAdapter extends BaseAdapter {

	private ArrayList<Artist> artistsList;
	private Context context;
	
	LayoutInflater inflater;
	
	public ArtistsListAdapter(Context context, ArrayList<Artist> artistsList){
		this.artistsList = artistsList;
		this.inflater = LayoutInflater.from(context);
		this.context = context;
		Log.i("ENTER IN ADAPTER LIST ARTISTS", artistsList.toString());
	}
	
	public int getCount(){
		return artistsList.size();
	}
	
	public Object getItem(int position) {
		return artistsList.get(position);
	}
	
	public long getItemId(int position) {
		return position;
	}

	private class ViewHolder {
		public TextView artistName;
		public TextView labelName;
		public TextView reviewCount;
		public ImageView artistImage;
	}

	public void clear() {
		artistsList.clear();
	}
	
	public void setList(ArrayList<Artist> list) {
		artistsList = list;
	}
	
	public View getView(int position, View view, ViewGroup parent) {
		ViewHolder holder = null;

		if (view == null) {
			holder = new ViewHolder();

			view = inflater.inflate(R.layout.artists_list_item, null);
			holder.artistName = (TextView) view.findViewById(R.id.concertName);
			holder.labelName = (TextView) view.findViewById(R.id.labelName);
			holder.reviewCount = (TextView) view.findViewById(R.id.reviewCount);
			holder.artistImage = (ImageView) view.findViewById(R.id.artistImage);
			
			String imageUrl = "http://lorempixel.com/230/130/";
			ImageLoader imageLoader = ImageLoader.getInstance();
			//imageLoader.init(ImageLoaderConfiguration.createDefault(context));
			imageLoader.displayImage(imageUrl, holder.artistImage);
			
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}

		Artist current = artistsList.get(position);
		Log.i("ARTIST IN ADAPTER", current.toString());
		holder.artistImage.setImageResource(R.drawable.concert1);
		holder.artistName.setText(current.getName());
		holder.labelName.setText(current.getLabel().getName());
		holder.reviewCount.setText("15 chroniques");

		Log.i("VIEW IN LIST ARTISTS", view.toString());
		return view;
	}

}
