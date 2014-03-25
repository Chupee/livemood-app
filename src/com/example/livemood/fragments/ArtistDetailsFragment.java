package com.example.livemood.fragments;

import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;

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
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class ArtistDetailsFragment extends Fragment {
	
	private final String TITLE = "Artiste";
	private String artistId;
	private Concert concert;
	
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
    Bundle savedInstanceState) {
    final View view = inflater.inflate(R.layout.artist_fragment, null);
    
    // Concert Id
    artistId = getArguments().getString("artistId");
    
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
			// TODO Auto-generated method stub
			ParseObject parseConcert = new ParseObject("concert");
			ParseObject parseArtist = new ParseObject("artist");
			JSONArray parseMoodsList = new JSONArray();
			ParseObject parseLabel = new ParseObject("label");
			ParseObject parsePlace = new ParseObject("place");
			
			Artist artist = null;
			Place place = null;
			Label label = null;
			parseArtist = arg0;
			parseConcert = parseArtist.getParseObject("concert");
			parseMoodsList = parseArtist.getJSONArray("moods");
			parseLabel = parseArtist.getParseObject("label");
			parsePlace = parseConcert.getParseObject("place");
			label = new Label(parseLabel.get("name").toString(), "");
			artist = new Artist(parseArtist.get("id").toString(), parseArtist.get("name").toString(), "", "", label);
			place = new Place(parsePlace.get("name").toString(), "");
			concert = new Concert(parseConcert.getObjectId(), artist, place, parseConcert.get("date").toString(), "");
			
			//MOODS MANAGEMENT
			HashMap<String, String> moodsList = new HashMap<String,String>();
	        for(int i = 0; i < parseMoodsList.length(); i++){
				String value;
				try {
					value = (String) parseMoodsList.getString(i);
					//artist.getMoodList
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
			
			//TextView
		    TextView tvArtistName = (TextView) view.findViewById(R.id.artistName);
		    TextView tvArtistLabel = (TextView) view.findViewById(R.id.artistLabel);
		    tvArtistName.setText(concert.getArtist().getName());
		    tvArtistLabel.setText(concert.getArtist().getLabel().getName());
		}
		
	});

    return view;
    
  }
  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);  
  }
  
  public static ArtistDetailsFragment newInstance(String concertId2) {
		ArtistDetailsFragment concertDetailsFragment = new ArtistDetailsFragment();
	    Bundle args = new Bundle();
	    args.putString("concertId", concertId2);
	    concertDetailsFragment.setArguments(args);
	    return concertDetailsFragment;
  }

}
