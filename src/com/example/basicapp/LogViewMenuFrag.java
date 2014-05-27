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

public class LogViewMenuFrag extends Fragment implements OnClickListener {

    @SuppressWarnings("unused")
	private static final String EVENT = "Event";
    private static final String LIFE = "LifeCycle";

	Button homeButton;
	Button editButton;
	
    LogViewMenuFragInterface activityCallback;

    //CONTAINER ACTIVITY MUST IMPLEMENT THIS INTERFACE
    public interface LogViewMenuFragInterface {
        public void editSelectedRecord();
        public void goToHomeScreen();
    }

    //THIS SECTION ENSURES INTERFACE COMPLIANCE
    @Override
    public void onAttach(Activity activity) { 
        super.onAttach(activity);
        Log.i(LIFE, "LogViewMenuFrag onAttach");
        
        try {
            activityCallback = (LogViewMenuFragInterface) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement LogViewMenuFragInterface");
        }
        
    }

    //THIS SECTION SETS BUTTON LISTENERS
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(LIFE, "LogViewMenuFrag onCreateView");
        
		//INFLATE THE LAYOUT FOR THIS FRAGMENT
        View rootView = inflater.inflate(R.layout.log_view_menu, container, false);
        
        //SET BUTTON CLICK LISTENERS
        homeButton = (Button) rootView.findViewById(R.id.log_view_cancel_button);
        homeButton.setOnClickListener(this); 
        editButton = (Button) rootView.findViewById(R.id.log_view_edit_button);
        editButton.setOnClickListener(this); 
        
        //RETURN THE VIEW
        return rootView;
    }
	
	//THIS SECTION IS ACTIVATED WHEN A BUTTON IS CLICKED
	@Override  
    public void onClick(View v) { 
        Log.i(LIFE, "LogViewMenuFrag onClick");
		
		switch (v.getId()) {
		
		//ADDING CASES FOR DIFFERENT BUTTON IDS
		case R.id.log_view_cancel_button:
			activityCallback.goToHomeScreen();
			break;
		case R.id.log_view_edit_button:
			activityCallback.editSelectedRecord();
			break;
		}
    }
	
   /* ____________________________________________
	*
	*	THIS AREA IS FOR ADDING SUPPORTING METHODS
	* ____________________________________________
    */
	
}
