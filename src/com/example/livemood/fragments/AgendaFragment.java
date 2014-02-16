package com.example.livemood.fragments;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.example.livemood.R;
import com.example.livemood.adapters.ConcertsListAdapter;
import com.example.livemood.models.Artist;
import com.example.livemood.models.Concert;
import com.example.livemood.models.Dig;
import com.example.livemood.models.Label;
import com.example.livemood.models.Mood;
import com.example.livemood.models.Place;
import com.example.livemood.models.ReferenceArtist;

public class AgendaFragment extends Fragment {
 
	/* Concerts list */
	private ArrayList<Concert> concertsList;
	private ListView lvListe;
	private ConcertsListAdapter adapter;
	
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
    Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.agenda_fragment, null);
    
    //
	// Hard data (me gusta)
	//
	Label labello = new Label("Labello", "labello.png", new ArrayList<Artist>());
	
	Mood sexy = new Mood("Sexy", new ArrayList<Artist>());
	Mood chevaleresque = new Mood("chevaleresque", new ArrayList<Artist>());
	Mood rebelle = new Mood("Rebelle", new ArrayList<Artist>());
	
	Artist birdyHunt = new Artist("Birdy Hunt", "birdyhunt.png", "birdyhunt-cover.png", labello, new ArrayList<Dig>(), new ArrayList<Concert>(), new ArrayList<Mood>(), new ArrayList<ReferenceArtist>());
	birdyHunt.getMoodsList().add(sexy);
	birdyHunt.getMoodsList().add(rebelle);
	birdyHunt.getMoodsList().add(chevaleresque);
	rebelle.getArtistsList().add(birdyHunt);
	chevaleresque.getArtistsList().add(birdyHunt);
	sexy.getArtistsList().add(birdyHunt);
	labello.getArtistsList().add(birdyHunt);
	
	Place bataclan = new Place("Bataclan", "28 rue du Swag", new ArrayList<Concert>());
	
	Concert concert1 = new Concert(birdyHunt, bataclan, "2013-11-28 20:00:00", "concert1.png");
	birdyHunt.getConcertsList().add(concert1);
	bataclan.getConcertsList().add(concert1);
	
	//
	// List
	//
	concertsList = new ArrayList<Concert>();
	concertsList.add(concert1);
	concertsList.add(concert1);
	concertsList.add(concert1);
	concertsList.add(concert1);
	
	lvListe = (ListView)view.findViewById(R.id.concertsList);
	adapter = new ConcertsListAdapter(getActivity().getApplicationContext(), concertsList);
    lvListe.setAdapter(adapter);
    
    lvListe.setOnItemClickListener(new OnItemClickListener() {
  	  @Override
  	  public void onItemClick(AdapterView<?> parent, View view,
  	    int position, long id) {
  	    Toast.makeText(getActivity().getApplicationContext(),
  	      "Click ListItem Number " + position, Toast.LENGTH_LONG)
  	      .show();
  	    
  	    // Insert the fragment by replacing any existing fragment
  	    Fragment fragment = new ConcertDetailsFragment();
  	    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction()
                       .replace(R.id.content_frame, fragment)
                       .addToBackStack(fragment.getTag())
                       .commit();

        // Update the title
        getActivity().getActionBar().setTitle(("Concert"));
        
  	  }
  	}); 
    
    return view;
  }
  
  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);  
  }
  

}
