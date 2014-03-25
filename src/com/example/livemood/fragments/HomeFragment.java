package com.example.livemood.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.livemood.R;


public class HomeFragment extends Fragment {
	
	private final String TITLE = "Accueil";
	private Button buttonGoToAgenda;
	private Button buttonGoToPopularArtists;
	
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
    Bundle savedInstanceState) {
    
	  View view = inflater.inflate(R.layout.home_fragment, null);
	  buttonGoToAgenda = (Button)view.findViewById(R.id.home_button_agenda);
	  buttonGoToPopularArtists = (Button)view.findViewById(R.id.home_button_popularArtists);
	  
	  	  
    //
    // Update action bar
    //
    getActivity().getActionBar().setTitle(TITLE);
    
    return view;
  }
  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);  
  } 

}
