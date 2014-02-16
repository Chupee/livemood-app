package com.example.livemood.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.livemood.R;


public class ProfileFragment extends Fragment {
	
	private final String TITLE = "Mon profil";
	
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
    Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.profile_fragment, null);

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
