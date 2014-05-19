package com.example.basicapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class FragmentOne extends Fragment implements OnClickListener {

	Button button;
    private static final String EVENT = "Event";
	
    OnButtonPush activityCallback;

    // Container Activity must implement this interface
    public interface OnButtonPush {
        public void onButtonPush(String string);
    }

    //this section verifies the interface compliance
    @Override
    public void onAttach(Activity activity) { 
        super.onAttach(activity);
        
        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            activityCallback = (OnButtonPush) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    //setting button listeners
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_one, container, false);
        
        //set button click listeners
        button = (Button) rootView.findViewById(R.id.main_button);
        button.setOnClickListener(this); 
        
        return rootView;
    }
	
	//this section is activated when a click event happens
	@Override  
    public void onClick(View v) { 
		switch (v.getId()) {
		
		//adding cases for different BUTTON ids
	    case R.id.main_button:
	    	activityCallback.onButtonPush("onClick TEST");
	        break;
	        
	  }
		Log.i(EVENT, "BUTTON CLICKED");
    }
	
   /* ____________________________________________
	*
	*	THIS AREA IS FOR ADDING SUPPORTING METHODS
	* ____________________________________________
    */
	
	public void setHint(String string) {
		View rootView = getView().findViewById(R.id.main_text);
		EditText textBox = (EditText) rootView.findViewById(R.id.main_text);
		textBox.setHint(string);
	}
	
}
