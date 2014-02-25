package com.example.livemood.fragments;

import java.util.ArrayList;

import org.apmem.tools.layouts.FlowLayout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ListView;

import com.example.livemood.R;
import com.example.livemood.adapters.DigsListAdapter;
import com.example.livemood.models.Artist;
import com.example.livemood.models.Concert;
import com.example.livemood.models.Dig;
import com.example.livemood.models.Digger;
import com.example.livemood.models.Label;
import com.example.livemood.models.Mood;
import com.example.livemood.models.Place;
import com.example.livemood.models.ReferenceArtist;
import com.example.livemood.models.Style;
import com.example.livemood.models.User;
import com.example.livemood.views.LMTextView;

public class ConcertDetailsFragment extends Fragment {
	
	private final String TITLE = "Concert";
	private int concertId;
	
	private FlowLayout tagsLayout;
	private LMTextView[] tvTags;
	private ArrayList<String> tagNamesList;
	
	private ListView lvDigsList;
	private DigsListAdapter digsAdapter;
	
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
    Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.concert_fragment, null);
    tagsLayout = (FlowLayout)view.findViewById(R.id.tagsLayout);
    
    // Concert Id
    concertId = getArguments().getInt("concertId", 0);
	try {
		if(concertId == 0) { throw new Exception("Erreur : le concert s�lectionn� n'existe pas."); }
	} catch (Exception e) {
		e.printStackTrace();
	}
    
    // Update action bar
    getActivity().getActionBar().setTitle(TITLE);
    
    //
	// Hard data (me gusta)
    User chupee = new User("chupee", "tamere", "julie.chupee@gmail.com", new ArrayList<Artist>(), new ArrayList<Digger>(), new ArrayList<Concert>());

    Digger diggingChupee = new Digger(chupee, new ArrayList<Dig>());
    
    Dig dig1 = new Dig(diggingChupee, "La grosse dig !", "Je n'ai jamais vu un tel dig de ma vie ! J'irais le revoir sans h�siter !", "2013-11-28", 3);
    Dig dig2 = new Dig(diggingChupee, "La grosse dig le retour !", "Je n'ai jamais vu un tel dig de ma vie ! J'irais le revoir sans h�siter !", "2013-11-28", 2);
    diggingChupee.getDigsList().add(dig1);
    diggingChupee.getDigsList().add(dig2);
    
	Label labello = new Label("Labello", "labello.png", new ArrayList<Artist>());
	
	Mood sexy = new Mood("Sexy", new ArrayList<Artist>());
	Mood chevaleresque = new Mood("chevaleresque", new ArrayList<Artist>());
	Mood rebelle = new Mood("Rebelle", new ArrayList<Artist>());
	
	Style rock = new Style("Rock", new ArrayList<Artist>());
	Style medieval = new Style("M�di�val", new ArrayList<Artist>());
	
	Artist birdyHunt = new Artist("Birdy Hunt", "birdyhunt.png", "birdyhunt-cover.png", labello, new ArrayList<Dig>(), new ArrayList<Concert>(), new ArrayList<Style>(),  new ArrayList<Mood>(), new ArrayList<ReferenceArtist>());
	birdyHunt.getMoodsList().add(sexy);
	birdyHunt.getMoodsList().add(rebelle);
	birdyHunt.getMoodsList().add(chevaleresque);
	rebelle.getArtistsList().add(birdyHunt);
	chevaleresque.getArtistsList().add(birdyHunt);
	sexy.getArtistsList().add(birdyHunt);
	labello.getArtistsList().add(birdyHunt);
	
	Artist fifou = new Artist("Fifou Odrezal", "birdyhunt.png", "birdyhunt-cover.png", labello, new ArrayList<Dig>(), new ArrayList<Concert>(), new ArrayList<Style>(),  new ArrayList<Mood>(), new ArrayList<ReferenceArtist>());
	fifou.getMoodsList().add(sexy);
	fifou.getMoodsList().add(rebelle);
	fifou.getMoodsList().add(chevaleresque);
	fifou.getStylesList().add(rock);
	fifou.getStylesList().add(medieval);
	rebelle.getArtistsList().add(fifou);
	chevaleresque.getArtistsList().add(fifou);
	sexy.getArtistsList().add(fifou);
	labello.getArtistsList().add(fifou);
	rock.getArtistsList().add(fifou);
	medieval.getArtistsList().add(fifou);
	
	fifou.getDigsList().add(dig1);
	fifou.getDigsList().add(dig2);
	
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
    
    // Tags list
    tagNamesList = new ArrayList<String>();
    for ( Mood mood : concert.getArtist().getMoodsList() ) {
    	Log.i("MOOD", mood.getName());
    	tagNamesList.add(mood.getName());
	}
    for ( ReferenceArtist reference : concert.getArtist().getReferencesList() ) {
    	tagNamesList.add(reference.getName());
	}
    for ( Style style : concert.getArtist().getStylesList() ) {
    	tagNamesList.add(style.getName());
	}
    
    tvTags = new LMTextView[tagNamesList.size()];
    for (int i = 0; i < tagNamesList.size(); i++) {
    	tvTags[i]= new LMTextView(getActivity());
		tvTags[i].setText(tagNamesList.get(i));
		tvTags[i].setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
		tvTags[i].setBackgroundColor(getResources().getColor(R.color.tag));
		tvTags[i].setTextColor(getResources().getColor(R.color.tagText));
		tvTags[i].setPadding(4, 7, 4, 7);
		tagsLayout.addView(tvTags[i]);
	}
    
    //Digs list
    lvDigsList = (ListView)view.findViewById(R.id.digsListView);
	digsAdapter = new DigsListAdapter(getActivity().getApplicationContext(), concert.getArtist().getDigsList());
	lvDigsList.setAdapter(digsAdapter);

    
    // Layouts
    LMTextView tvArtistName = (LMTextView) view.findViewById(R.id.artistName);
    LMTextView tvArtistLabel = (LMTextView) view.findViewById(R.id.artistLabel);
    LMTextView tvDateAndPlace = (LMTextView) view.findViewById(R.id.dateAndPlace);
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
