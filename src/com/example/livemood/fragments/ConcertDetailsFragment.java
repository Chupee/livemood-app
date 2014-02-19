package com.example.livemood.fragments;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.livemood.R;
import com.example.livemood.models.Artist;
import com.example.livemood.models.Concert;
import com.example.livemood.models.Dig;
import com.example.livemood.models.Label;
import com.example.livemood.models.Mood;
import com.example.livemood.models.Place;
import com.example.livemood.models.ReferenceArtist;

public class ConcertDetailsFragment extends Fragment {
	
	private final String TITLE = "Concert";
	private int concertId;
	
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
    Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.concert_fragment, null);
    
    // Concert Id
    concertId = getArguments().getInt("concertId", 0);
	try {
		if(concertId == 0) { throw new Exception("Erreur : le concert sélectionné n'existe pas."); }
	} catch (Exception e) {
		e.printStackTrace();
	}
    
    // Update action bar
    getActivity().getActionBar().setTitle(TITLE);
    
    //
	// Hard data (me gusta)
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
	
	Artist fifou = new Artist("Fifou Odrezal", "birdyhunt.png", "birdyhunt-cover.png", labello, new ArrayList<Dig>(), new ArrayList<Concert>(), new ArrayList<Mood>(), new ArrayList<ReferenceArtist>());
	fifou.getMoodsList().add(sexy);
	fifou.getMoodsList().add(rebelle);
	fifou.getMoodsList().add(chevaleresque);
	rebelle.getArtistsList().add(fifou);
	chevaleresque.getArtistsList().add(fifou);
	sexy.getArtistsList().add(fifou);
	labello.getArtistsList().add(fifou);
	
	Place bataclan = new Place("Bataclan", "28 rue du Swag", new ArrayList<Concert>());
	
	Concert concert1 = new Concert(123456789, birdyHunt, bataclan, "2013-11-28", "concert1.png");
	Concert concert2 = new Concert(223456789, fifou, bataclan, "2013-11-28", "concert1.png");
	Concert concert3 = new Concert(323456789, birdyHunt, bataclan, "2013-11-28", "concert1.png");
	Concert concert4 = new Concert(423456789, fifou, bataclan, "2013-11-28", "concert1.png");
	birdyHunt.getConcertsList().add(concert1);
	birdyHunt.getConcertsList().add(concert3);
	fifou.getConcertsList().add(concert2);
	fifou.getConcertsList().add(concert4);
	
	bataclan.getConcertsList().add(concert1);
	bataclan.getConcertsList().add(concert2);
	bataclan.getConcertsList().add(concert3);
	bataclan.getConcertsList().add(concert4);
	
	//
	// List
	//
	ArrayList<Concert> concertsList = new ArrayList<Concert>();
	concertsList.add(concert1);
	concertsList.add(concert2);
	concertsList.add(concert3);
	concertsList.add(concert4);
    
    // Concert
    Concert concert = concertsList.get(1); // TODO : get the concert in db depending on the concertId
    
    //TextView
    TextView tvArtistName = (TextView) view.findViewById(R.id.artistName);
    TextView tvArtistLabel = (TextView) view.findViewById(R.id.artistLabel);
    TextView tvDateAndPlace = (TextView) view.findViewById(R.id.dateAndPlace);
    tvArtistName.setText(concert.getArtist().getName());
    tvArtistLabel.setText(concert.getArtist().getLabel().getName());
    tvDateAndPlace.setText(concert.getDate()+" - "+concert.getPlace().getName());

    
    return view;
  }
  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);  
  }
  
  public static ConcertDetailsFragment newInstance(int concertId) {
		ConcertDetailsFragment concertDetailsFragment = new ConcertDetailsFragment();
	    Bundle args = new Bundle();
	    args.putInt("concertId", concertId);
	    concertDetailsFragment.setArguments(args);
	    return concertDetailsFragment;
  }

}
