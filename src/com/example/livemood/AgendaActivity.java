package com.example.livemood;

import android.app.ActionBar;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.livemood.fragments.AgendaFragment;
import com.example.livemood.fragments.HomeFragment;
import com.example.livemood.fragments.PopularArtistsFragment;



public class AgendaActivity extends FragmentActivity {
	
	/* Drawer Navigation */
	private final int homePosition =	 			0;
	private final int agendaPosition = 				1;
	private final int popularArtistsPosition = 		2;

	private String[] drawerItems;
	private DrawerLayout drawerLayout;
	private ListView drawerList;
	private ActionBarDrawerToggle drawerToggle;
	private CharSequence title;


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
    
    @Override
    public void onBackPressed(){
    	Log.d("Livemood", "onBackPressed Called");
        FragmentManager fm = getSupportFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            Log.i("MainActivity", "popping backstack");
            fm.popBackStack();
        } else {
            Log.i("MainActivity", "nothing on backstack, calling super");
            super.onBackPressed();  
        }
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
    	Fragment fragment = null;
    	switch (position) {
			case homePosition:
				fragment = new HomeFragment();
				break;
			case agendaPosition:
				fragment = new AgendaFragment();
				break;
			case popularArtistsPosition:
				fragment = new PopularArtistsFragment();
				break;
			default:
				fragment = new HomeFragment();
				break;
		}
        
        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                       .replace(R.id.content_frame, fragment)
                       .addToBackStack(fragment.getTag())
                       .commit();

        // Highlight the selected item, update the title, and close the drawer
        drawerList.setItemChecked(position, true);
        drawerLayout.closeDrawer(drawerList);
    }

}
