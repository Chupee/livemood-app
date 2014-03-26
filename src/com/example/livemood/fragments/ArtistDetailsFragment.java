package com.example.livemood.fragments;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

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
import com.example.livemood.adapters.ConcertsListAdapter;
import com.example.livemood.models.Artist;
import com.example.livemood.models.Concert;
import com.example.livemood.models.Label;
import com.example.livemood.models.Place;
import com.example.livemood.views.LMTextView;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class ArtistDetailsFragment extends Fragment {
	
	private final String TITLE = "Artiste";
	private String artistId;
	private Artist artist;
	private Label label;
	private FlowLayout tagsLayout;
	private ArrayList<Concert> concertsList = new ArrayList<Concert>();
	private ListView lvListe;
	private ConcertsListAdapter adapter;
	
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
    Bundle savedInstanceState) {
    final View view = inflater.inflate(R.layout.artist_fragment, null);
    tagsLayout = (FlowLayout)view.findViewById(R.id.tagsLayout);
    
    // Concert Id
    artistId = getArguments().getString("artistId");
    
    @SuppressWarnings("unused")
	String artistid = artistId;
    
    /*
     * BESOIN DE :
     * label
     * tags
     *  
     *
     */
	try {
		if(artistId == "") { throw new Exception("Erreur : l'artiste s�lectionn� n'existe pas."); }
	} catch (Exception e) {
		e.printStackTrace();
	}
    
    // Update action bar
    getActivity().getActionBar().setTitle(TITLE);
    
    ParseQuery<ParseObject> query = ParseQuery.getQuery("artist");
    query.include("label");
    query.include("concert");
	query.include("concert.place");
	
	query.getInBackground(artistId, new GetCallback<ParseObject>() {
		
		@SuppressWarnings("unused")
		@Override
		public void done(ParseObject arg0, ParseException arg1) {
			
			ParseObject parseConcert = new ParseObject("concert");
			ParseObject parseArtist = new ParseObject("artist");
			JSONArray parseMoodsList = new JSONArray();
			ParseObject parseLabel = new ParseObject("label");
			parseArtist = arg0;
			
			// Update header TextView
	        
	        parseLabel = parseArtist.getParseObject("label");
			label = new Label(parseLabel.get("name").toString(), "");
			artist = new Artist(parseArtist.getObjectId(), parseArtist.get("name").toString(), "", "", label);
			
		    TextView tvArtistName = (TextView) view.findViewById(R.id.artistName);
		    TextView tvArtistLabel = (TextView) view.findViewById(R.id.artistLabel);
		    tvArtistName.setText(artist.getName());
		    tvArtistLabel.setText(artist.getLabel().getName());
			
			// Fill tags/moods corresponding to the concert
		    
			parseMoodsList = parseArtist.getJSONArray("moods");
	        for(int i = 0; i < parseMoodsList.length(); i++){
				try {
					String value = (String) parseMoodsList.getString(i);
					LMTextView currentTag = new LMTextView(getActivity());
					currentTag.setText(value);
					currentTag.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
					currentTag.setBackgroundColor(getResources().getColor(R.color.tag));
					currentTag.setTextColor(getResources().getColor(R.color.tagText));
					currentTag.setPadding(4, 7, 4, 7);
					tagsLayout.addView(currentTag);
				} catch (JSONException e) {
					e.printStackTrace();
				}
	        }
	        
	        // Get the upcoming concerts
	        
	        ParseQuery<ParseObject> concertQuery = ParseQuery.getQuery("concert");
	        concertQuery.include("artist");
	        concertQuery.whereEqualTo("artist", parseArtist);
	        
	        concertQuery.findInBackground(new FindCallback<ParseObject>() {
				
			    public void done(List<ParseObject> concertList, ParseException e) {
			        if (e == null) {
			        	
			        	// Update text view
			        	TextView comingConcertCount = (TextView) view.findViewById(R.id.concertCount);
			        	if(concertList.size() > 1) {
			        		comingConcertCount.setText(concertList.size() + " concerts à venir");
			        	} else {
			        		comingConcertCount.setText(String.valueOf(concertList.size()) + " concert à venir");
			        	}
			        	
			        	// Update concert list
			        	
			        	ParseObject parseConcert = new ParseObject("concert");
						ParseObject parsePlace = new ParseObject("place");
						
						Concert concert = null;
						Place place = null;
			        	String concertDate = null;
						
			        	for(Iterator<ParseObject> it = concertList.iterator(); it.hasNext();) {
			        		
			        		parseConcert = it.next();
			        		parsePlace = parseConcert.getParseObject("place");
			        		
			        		concertDate = new String("soon");
							try {
								Date date = new SimpleDateFormat("EEEE MMMM d hh:mm:ss z yyyy", Locale.ENGLISH).parse(parseConcert.get("date").toString());
								SimpleDateFormat format = new SimpleDateFormat("WW MMMM yyyy", Locale.FRANCE);
								concertDate = format.format(date);
							} catch (java.text.ParseException ee) {
								Log.d("PARSE ERROR", "error");
								ee.printStackTrace();
							}
							
							try {
								parsePlace.fetchIfNeeded();
							} catch (ParseException e1) {
								e1.printStackTrace();
							}

							place = new Place(parsePlace.getString("name"), "");
							concert = new Concert(parseConcert.getObjectId(), artist, place, concertDate, "");

							concertsList.add(concert);
							
							lvListe = (ListView)view.findViewById(R.id.concertsListView);
							adapter = new ConcertsListAdapter(getActivity().getApplicationContext(), concertsList);
							lvListe.setAdapter(adapter);
						}
			        	
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
  
  public static ArtistDetailsFragment newInstance(String artistId) {
	  
		ArtistDetailsFragment artistDetailsFragment = new ArtistDetailsFragment();
	    Bundle args = new Bundle();
	    args.putString("artistId", artistId);
	    artistDetailsFragment.setArguments(args);
	    return artistDetailsFragment;
	    
  }

}
