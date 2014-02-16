package com.example.livemood.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.livemood.R;


public class MyConcertsFragment extends Fragment {
	
	private final String TITLE = "Mes concerts";
	
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
    Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.myconcerts_fragment, null);

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
