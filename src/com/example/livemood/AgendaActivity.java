package com.example.livemood;

import java.util.ArrayList;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.livemood.adapters.ConcertsListAdapter;
import com.example.livemood.fragments.OpertingSystemFragment;
import com.example.livemood.models.Artist;
import com.example.livemood.models.Concert;
import com.example.livemood.models.Dig;
import com.example.livemood.models.Label;
import com.example.livemood.models.Mood;
import com.example.livemood.models.Place;
import com.example.livemood.models.ReferenceArtist;

public class AgendaActivity extends Activity {
	
	/* Drawer Navigation */
	private String[] drawerItems;
	private DrawerLayout drawerLayout;
	private ListView drawerList;
	private ActionBarDrawerToggle drawerToggle;
	private CharSequence title;
	
	/* Concerts list */
	private ArrayList<Concert> concertsList;
	private ListView lvListe;
	private ConcertsListAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_agenda);
		
		//
		// Action bar
		//
	    ActionBar actionBar = getActionBar();
	    actionBar.setHomeButtonEnabled(true);
	    title = getActionBar().getTitle();
	    
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
		drawerItems = getResources().getStringArray(R.array.items);
		// Set the adapter for the list view
        drawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, R.id.itemName, drawerItems));
        // Set the list's click listener
        drawerList.setOnItemClickListener(new DrawerItemClickListener());
        // Drawer toggle
        drawerToggle = new ActionBarDrawerToggle(this,               
                drawerLayout,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* nav drawer icon to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description */
                R.string.drawer_close  /* "close drawer" description */) {

        	/** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                // getActionBar().setTitle(title);
            }
            
            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                // getActionBar().setTitle("Open Drawer");
            }
        };
        
        // Set the drawer toggle as the DrawerListener
        drawerLayout.setDrawerListener(drawerToggle);
        
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
        
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
		return super.onCreateOptionsMenu(menu);
	}
	
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (drawerToggle.onOptionsItemSelected(item)) {
          return true;
        }
        // Handle your other action bar items...
        switch (item.getItemId()) {
		    case R.id.action_settings:
		      Toast.makeText(this, "Settings selected", Toast.LENGTH_LONG).show();
		      break;
		
		    default:
		      break;
		}
        return super.onOptionsItemSelected(item);
    }
	
	private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            selectItem(position);
        }
    }
	
	/** Swaps fragments in the main content view */
    private void selectItem(int position) {
        // create a new fragment and specify the planet to show based on position
        Fragment fragment = new OpertingSystemFragment();
        Bundle args = new Bundle();
        args.putInt(OpertingSystemFragment.ARG_OS, position);
        fragment.setArguments(args);

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                       .replace(R.id.content_frame, fragment)
                       .commit();

        // Highlight the selected item, update the title, and close the drawer
        drawerList.setItemChecked(position, true);
        getActionBar().setTitle((drawerItems[position]));
        drawerLayout.closeDrawer(drawerList);
    }

}
