package com.example.livemood.fragments;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

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
import android.widget.ListView;
import android.widget.TextView;

import com.example.livemood.R;
import com.example.livemood.adapters.DigsListAdapter;
import com.example.livemood.models.Artist;
import com.example.livemood.models.Concert;
import com.example.livemood.models.Dig;
import com.example.livemood.models.Digger;
import com.example.livemood.models.Label;
import com.example.livemood.models.Place;
import com.example.livemood.views.LMTextView;
import com.parse.FindCallback;
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
	private ArrayList<Dig> digsList = new ArrayList<Dig>();
	private ListView lvListe;
	private DigsListAdapter adapter;

	
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
    
    ParseQuery<ParseObject> query = ParseQuery.getQuery("concert");
    query.include("artist");
    query.getInBackground(getArguments().getString("concertId"), new GetCallback<ParseObject>() {
    	
    	public void done(ParseObject arg0, ParseException arg1) {
			
    		ParseObject parseConcert = new ParseObject("concert");
    		ParseObject parseArtist = new ParseObject("artist");
			JSONArray parseMoodsList = new JSONArray();
			parseConcert = arg0;
			parseArtist = parseConcert.getParseObject("artist");
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

		    
		    ParseQuery<ParseObject> digQuery = ParseQuery.getQuery("dig");
			digQuery.include("digger");
			digQuery.whereEqualTo("concert", parseConcert);
			
			Log.i("DIG QUERY", "RETRIEVING DIG QUERIE");
			/*Digger digger = new Digger("Alexis Assadourian");
    		Dig dig = new Dig(digger, "L’unisson provoque parfois des miracles lorsque les textures s’en mêlent. Rugosité et force de frappe assurées produisent ce tonifiant bijou de simplicité électrique.", 2);
			digsList.add(dig);
			lvListe = (ListView)view.findViewById(R.id.digsListView);
			adapter = new DigsListAdapter(getActivity().getApplicationContext(), digsList);
			lvListe.setAdapter(adapter);*/
			
			digQuery.findInBackground(new FindCallback<ParseObject>() {
				
			    public void done(List<ParseObject> digList, ParseException e) {
			        if (e == null) {
			        	for(Iterator<ParseObject> it = digList.iterator(); it.hasNext();) {
			        		ParseObject parseDig = new ParseObject("dig");
			        		ParseObject parseDigger = new ParseObject("digger");
			        		parseDig = it.next();
			        		parseDigger = parseDig.getParseObject("digger");
			        		Digger digger = new Digger(parseDigger.getString("name"));
			        		Dig dig = new Dig(digger, parseDig.getString("text"), parseDig.getInt("score"));
							digsList.add(dig);
						}
			        	lvListe = (ListView)view.findViewById(R.id.digsListView);
						adapter = new DigsListAdapter(getActivity().getApplicationContext(), digsList);
						lvListe.setAdapter(adapter);
			            Log.i("DIGS LIST", "Retrieved " + digsList.size() + " scores");
			        } else {
			            Log.e("DIGS LIST", "Error: " + e.getMessage());
			        }
			    }
			});

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
