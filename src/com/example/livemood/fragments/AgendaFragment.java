package com.example.livemood.fragments;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.livemood.R;
import com.example.livemood.adapters.ConcertsListAdapter;
import com.example.livemood.models.Artist;
import com.example.livemood.models.Concert;
import com.example.livemood.models.Label;
import com.example.livemood.models.Place;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class AgendaFragment extends Fragment {
 
	private final String TITLE = "Agenda";
	
	/* Concerts list */
	private ArrayList<Concert> concertsList;
	private ListView lvListe;
	private ConcertsListAdapter adapter;
	
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
    Bundle savedInstanceState) {
    final View view = inflater.inflate(R.layout.agenda_fragment, null);
    
    //
    // Update action bar
    //
    getActivity().getActionBar().setTitle(TITLE);
    
	//Parse
	ParseQuery<ParseObject> query = ParseQuery.getQuery("concert");
	query.include("place");
	query.include("artist");
	query.include("artist.label");
	
	concertsList = new ArrayList<Concert>();
	query.findInBackground(new FindCallback<ParseObject>() {
		@Override
		public void done(List<ParseObject> arg0, ParseException arg1) {
			Log.d("CONCERT_LIST", arg0.get(0).get("image").toString());
			//
			// List
			//
			ParseObject parseConcert = new ParseObject("concert");
			ParseObject parseArtist = new ParseObject("artist");
			ParseObject parseLabel = new ParseObject("label");
			ParseObject parsePlace = new ParseObject("place");
			
			Artist artist = null;
			Concert concert = null;
			Place place = null;
			Label label = null;
			
			for(Iterator<ParseObject> it = arg0.iterator(); it.hasNext();) {
				parseConcert = it.next();
				parseArtist = parseConcert.getParseObject("artist");
				parseLabel = parseArtist.getParseObject("label");
				parsePlace = parseConcert.getParseObject("place");
				label = new Label(parseLabel.get("name").toString(), "");
				artist = new Artist(parseArtist.get("name").toString(), "", "", label);
				place = new Place(parsePlace.get("name").toString(), "");
				concert = new Concert(parseConcert.getObjectId(), artist, place, parseConcert.get("date").toString(), "");
				concertsList.add(concert);
				Log.d("OK", "OK");
			}
			lvListe = (ListView)view.findViewById(R.id.concertsList);
			adapter = new ConcertsListAdapter(getActivity().getApplicationContext(), concertsList);
		    lvListe.setAdapter(adapter);
		    
		    lvListe.setOnItemClickListener(new OnItemClickListener() {
		  	  @Override
		  	  public void onItemClick(AdapterView<?> parent, View view,
		  	    int position, long id) {
		  	    String concertId = concertsList.get(position).getId();
		  	    // Insert the fragment by replacing any existing fragment
		  	    Fragment fragment = ConcertDetailsFragment.newInstance(concertId);
		  	    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
		        fragmentManager.beginTransaction()
		                       .replace(R.id.content_frame, fragment)
		                       .addToBackStack(fragment.getTag())
		                       .commit();

		        // Update the title
		        getActivity().getActionBar().setTitle("Concert");
		        
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
  

}
