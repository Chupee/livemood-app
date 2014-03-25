package com.example.livemood.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
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

public class ConcertDetailsFragment extends Fragment {
	
	private final String TITLE = "Concert";
	private String concertId;
	private Concert concert;
	
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
    Bundle savedInstanceState) {
    final View view = inflater.inflate(R.layout.concert_fragment, null);
    
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
		
		@Override
		public void done(ParseObject arg0, ParseException arg1) {
			// TODO Auto-generated method stub
			ParseObject parseConcert = new ParseObject("concert");
			ParseObject parseArtist = new ParseObject("artist");
			ParseObject parseLabel = new ParseObject("label");
			ParseObject parsePlace = new ParseObject("place");
			
			Artist artist = null;
			Place place = null;
			Label label = null;
			
			parseConcert = arg0;
			parseArtist = parseConcert.getParseObject("artist");
			parseLabel = parseArtist.getParseObject("label");
			parsePlace = parseConcert.getParseObject("place");
			label = new Label(parseLabel.get("name").toString(), "");
			artist = new Artist(parseArtist.get("name").toString(), "", "", label);
			place = new Place(parsePlace.get("name").toString(), "");
			concert = new Concert(parseConcert.getObjectId(), artist, place, parseConcert.get("date").toString(), "");
			
			//TextView
		    TextView tvArtistName = (TextView) view.findViewById(R.id.artistName);
		    TextView tvArtistLabel = (TextView) view.findViewById(R.id.artistLabel);
		    TextView tvDateAndPlace = (TextView) view.findViewById(R.id.dateAndPlace);
		    tvArtistName.setText(concert.getArtist().getName());
		    tvArtistLabel.setText(concert.getArtist().getLabel().getName());
		    tvDateAndPlace.setText(concert.getDate()+" - "+concert.getPlace().getName());
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
