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
	query.include("place");
	query.include("artist");
	query.include("artist.label");
	
	query.getInBackground(concertId, new GetCallback<ParseObject>() {
		
		@SuppressWarnings("unused")
		@Override
		public void done(ParseObject arg0, ParseException arg1) {
			// TODO Auto-generated method stub
			ParseObject parseConcert = new ParseObject("concert");
			ParseObject parseArtist = new ParseObject("artist");
			JSONArray parseMoodsList = new JSONArray();
			ParseObject parseLabel = new ParseObject("label");
			ParseObject parsePlace = new ParseObject("place");
			
			Artist artist = null;
			Place place = null;
			Label label = null;
			parseConcert = arg0;
			parseArtist = parseConcert.getParseObject("artist");
			parseMoodsList = parseArtist.getJSONArray("moods");
			//[{"__type":"Pointer","className":"mood","objectId":"rjvj1sMASP"},{"__type":"Pointer","className":"mood","objectId":"gxQB4dAYkH"}]
			
			parseMoodsList = parseArtist.getJSONArray("moods");
			parseLabel = parseArtist.getParseObject("label");
			parsePlace = parseConcert.getParseObject("place");
			label = new Label(parseLabel.get("name").toString(), "");
			artist = new Artist(parseArtist.getObjectId(), parseArtist.get("name").toString(), "", "", label);
			place = new Place(parsePlace.get("name").toString(), "");
			concert = new Concert(parseConcert.getObjectId(), artist, place, parseConcert.get("date").toString(), "");
			
			//MOODS MANAGEMENT
			HashMap<String, String> moodsList = new HashMap<String,String>();
	        for(int i = 0; i < parseMoodsList.length(); i++){
				String value;
				try {
					value = (String) parseMoodsList.getString(i);
					LMTextView currentTag = new LMTextView(getActivity());
					currentTag.setText(value);
					Log.i("TVTAGS !!!!!!!!!!!!!!!!!", currentTag.toString());
					currentTag.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
					currentTag.setBackgroundColor(getResources().getColor(R.color.tag));
					currentTag.setTextColor(getResources().getColor(R.color.tagText));
					currentTag.setPadding(4, 7, 4, 7);
					tagsLayout.addView(currentTag);
					//artist.getMoodList
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
			
			//TextView
		    TextView tvArtistName = (TextView) view.findViewById(R.id.artistName);
		    TextView tvArtistLabel = (TextView) view.findViewById(R.id.artistLabel);
		    TextView tvDateAndPlace = (TextView) view.findViewById(R.id.dateAndPlace);
		    tvArtistName.setText(concert.getArtist().getName());
		    tvArtistLabel.setText(concert.getArtist().getLabel().getName());
		    tvDateAndPlace.setText(concert.getDate()+" - "+concert.getPlace().getName());
		    
		    ParseQuery<ParseObject> digQuery = ParseQuery.getQuery("dig");
			digQuery.include("digger");
			digQuery.whereEqualTo("artist", parseArtist);
			
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

    return view;
    
  }
  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);  
  }
  
  public static ConcertDetailsFragment newInstance(String concertId2) {
		ConcertDetailsFragment concertDetailsFragment = new ConcertDetailsFragment();
	    Bundle args = new Bundle();
	    args.putString("concertId", concertId2);
	    concertDetailsFragment.setArguments(args);
	    return concertDetailsFragment;
  }

}
