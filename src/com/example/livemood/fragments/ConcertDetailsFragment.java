package com.example.livemood.fragments;

import java.util.ArrayList;
import java.util.HashMap;

import org.apmem.tools.layouts.FlowLayout;
import org.json.JSONArray;
import org.json.JSONException;

import android.app.ActionBar.LayoutParams;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.livemood.R;
import com.example.livemood.models.Artist;
import com.example.livemood.models.Concert;
import com.example.livemood.models.Label;
import com.example.livemood.models.Place;
import com.example.livemood.views.LMTextView;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class ConcertDetailsFragment extends Fragment {
	
	private final String TITLE = "Concert";
	private String concertId;
	private Concert concert;
	private FlowLayout tagsLayout;
	private ArrayList<LMTextView> tvTags = new ArrayList<LMTextView>();

	
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
    Bundle savedInstanceState) {
    final View view = inflater.inflate(R.layout.concert_fragment, null);
    tagsLayout = (FlowLayout)view.findViewById(R.id.tagsLayout);

    // Concert Id
    concertId = getArguments().getString("concertId");
    
    /*
     * BESOIN DE :
     * label
     * artist
     * tags
     * place
     *  
     *
     */
	try {
		if(concertId == "") { throw new Exception("Erreur : le concert s�lectionn� n'existe pas."); }
	} catch (Exception e) {
		e.printStackTrace();
	}
    
    // Update action bar
    getActivity().getActionBar().setTitle(TITLE);
    
    ParseQuery<ParseObject> query = ParseQuery.getQuery("artist");
    query.getInBackground(getArguments().getString("artistID"), new GetCallback<ParseObject>() {
    	
    	public void done(ParseObject arg0, ParseException arg1) {
			
    		ParseObject parseArtist = new ParseObject("artist");
			JSONArray parseMoodsList = new JSONArray();
			parseArtist = arg0;
			parseMoodsList = parseArtist.getJSONArray("moods");
			
			//MOODS MANAGEMENT
	        for(int i = 0; i < parseMoodsList.length(); i++){
				String value;
				try {
					value = (String) parseMoodsList.getString(i);
					LMTextView currentTag = new LMTextView(getActivity());
					currentTag.setText(value);
					currentTag.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
					currentTag.setBackgroundColor(getResources().getColor(R.color.tag));
					currentTag.setTextColor(getResources().getColor(R.color.tagText));
					currentTag.setPadding(4, 7, 4, 7);
					tagsLayout.addView(currentTag);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
		}
    });
    
 	//TextView
    
  	TextView tvArtistName = (TextView) view.findViewById(R.id.artistName);
  	tvArtistName.setText(getArguments().getString("artistName"));
  	
  	TextView tvArtistLabel = (TextView) view.findViewById(R.id.artistLabel);
  	tvArtistLabel.setText(getArguments().getString("labelName"));
  	
  	TextView tvDateAndPlace = (TextView) view.findViewById(R.id.dateAndPlace);
  	tvDateAndPlace.setText(getArguments().getString("concertDate") + " - " + getArguments().getString("concertPlace"));
  	
  	/*String[] tags = getArguments().getStringArray("tags");
  	for(int i = 0; i < getArguments().getString("moods").length(); i++){
		LMTextView currentTag = new LMTextView(getActivity());
		currentTag.setText(tags[i]);
		currentTag.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
		currentTag.setBackgroundColor(getResources().getColor(R.color.tag));
		currentTag.setTextColor(getResources().getColor(R.color.tagText));
		currentTag.setPadding(4, 7, 4, 7);
		tagsLayout.addView(currentTag);
    }*/

    return view;
    
  }
  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);  
  }
  
  public static ConcertDetailsFragment newInstance(String artistID, String artistName, String labelName, String concertPlace, String concertDate, String thumbnail) {
		ConcertDetailsFragment concertDetailsFragment = new ConcertDetailsFragment();
	    Bundle args = new Bundle();
	    
	    args.putString("artistID", artistID);
	    args.putString("artistName", artistName);
	    args.putString("labelName", labelName);
	    args.putString("concertPlace", concertPlace);
	    args.putString("concertDate", concertDate);
	    args.putString("thumbnail", thumbnail);
	    
	    concertDetailsFragment.setArguments(args);
	    return concertDetailsFragment;
  }

}
