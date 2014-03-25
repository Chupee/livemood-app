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
import com.example.livemood.adapters.ArtistsListAdapter;
import com.example.livemood.models.Artist;
import com.example.livemood.models.Label;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;


public class PopularArtistsFragment extends Fragment {
	
	private final String TITLE = "Artistes en vogue";
	
	/* Concerts list */
	private ArrayList<Artist> artistsList;
	private ListView lvListe;
	private ArtistsListAdapter adapter;
	
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
    Bundle savedInstanceState) {
    final View view = inflater.inflate(R.layout.popularartists_fragment, null);

    //
    // Update action bar
    //
    getActivity().getActionBar().setTitle(TITLE);
    
    // Parse
  	ParseQuery<ParseObject> query = ParseQuery.getQuery("artist");
  	query.include("label");
    
    //
    // Artists List
    //
    artistsList = new ArrayList<Artist>();
    
    query.findInBackground(new FindCallback<ParseObject>() {
		@Override
		public void done(List<ParseObject> arg0, ParseException arg1) {

			ParseObject parseArtist = new ParseObject("artist");
			ParseObject parseLabel = new ParseObject("label");
			
			Artist artist = null;
			Label label = null;
			
			for(Iterator<ParseObject> it = arg0.iterator(); it.hasNext();) {
				parseArtist = it.next();
				parseLabel = parseArtist.getParseObject("label");
				label = new Label(parseLabel.get("name").toString(), "");
				artist = new Artist(parseArtist.get("id").toString(), parseArtist.get("name").toString(), "", "", label);
				Log.i("ARTIST PARSED", artist.toString());
				artistsList.add(artist);
			}
			Log.i("ARTIST LIST PARSED", artistsList.toString());
			lvListe = (ListView)view.findViewById(R.id.artistsList);
			adapter = new ArtistsListAdapter(getActivity().getApplicationContext(), artistsList);
			Log.i("ADAPTER", adapter.getItem(0).toString());
		    lvListe.setAdapter(adapter);
		    Log.i("ADAPTER FROM LV", lvListe.getAdapter().getItem(0).toString());
		    
      	    lvListe.setOnItemClickListener(new OnItemClickListener() {
		  	  @Override
		  	  public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		  	    String artistId = artistsList.get(position).getId();
		  	    // Insert the fragment by replacing any existing fragment
		  	    Fragment fragment = ArtistDetailsFragment.newInstance(artistId);
		  	    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
		        fragmentManager.beginTransaction()
		                       .replace(R.id.content_frame, fragment)
		                       .addToBackStack(fragment.getTag())
		                       .commit();

		        // Update the title
		        getActivity().getActionBar().setTitle("Artiste");
		        
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
