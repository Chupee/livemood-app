package com.example.livemood;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.livemood.adapters.ConcertsListAdapter;
import com.example.livemood.models.Artist;
import com.example.livemood.models.Concert;
import com.example.livemood.models.Dig;
import com.example.livemood.models.Label;
import com.example.livemood.models.Mood;
import com.example.livemood.models.Place;
import com.example.livemood.models.ReferenceArtist;

public class AgendaActivity extends Activity {
	
	/* Drawer Navigation */
	private DrawerLayout drawerLayout;
	private ListView drawerList;
	
	/* Concerts list */
	private ArrayList<Concert> concertsList;
	private ListView lvListe;
	private ConcertsListAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_agenda);

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
		// Drawer navigation
		//
		drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		drawerList = (ListView) findViewById(R.id.left_drawer);
		String[] drawerItems = getResources().getStringArray(R.array.items);
		// Set the adapter for the list view
        drawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, R.id.itemName, drawerItems));
        // Set the list's click listener
        //drawerList.setOnItemClickListener(new DrawerItemClickListener());
        
		//
		// List
		//
		concertsList = new ArrayList<Concert>();
		concertsList.add(concert1);
		concertsList.add(concert1);
		concertsList.add(concert1);
		concertsList.add(concert1);
		
		lvListe = (ListView)findViewById(R.id.concertsList);
		adapter = new ConcertsListAdapter(getApplicationContext(), concertsList);
	    lvListe.setAdapter(adapter);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.agenda, menu);
		return true;
	}

}
