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

import android.support.v4.app.FragmentActivity;

public class LogViewMenuFrag extends Fragment implements OnClickListener {

	Button homeButton;
	Button viewButton;
	Button editButton;

    private static final String EVENT = "Event";
	
    LogViewMenuFragInterface activityCallback;

    //CONTAINER ACTIVITY MUST IMPLEMENT THIS INTERFACE
    public interface LogViewMenuFragInterface {
        public void updateStatus(String string);
        public void viewLogEntry(int i);
        public void logCancel();
    }

    //THIS SECTION ENSURES INTERFACE COMPLIANCE
    @Override
    public void onAttach(Activity activity) { 
        super.onAttach(activity);
        
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
        
		//INFLATE THE LAYOUT FOR THIS FRAGMENT
        View rootView = inflater.inflate(R.layout.log_view_menu, container, false);
        
        //SET BUTTON CLICK LISTENERS
        homeButton = (Button) rootView.findViewById(R.id.log_menu_cancel);
        homeButton.setOnClickListener(this); 
        viewButton = (Button) rootView.findViewById(R.id.log_menu_view);
        viewButton.setOnClickListener(this); 
        editButton = (Button) rootView.findViewById(R.id.log_menu_edit);
        editButton.setOnClickListener(this); 
        
        return rootView;
    }
	
	//THIS SECTION IS ACTIVATED WHEN A BUTTON IS CLICKED
	@Override  
    public void onClick(View v) { 
		
		switch (v.getId()) {
		
		//adding cases for different BUTTON ids
    case R.id.log_menu_cancel:
    	activityCallback.logCancel();
        break;
    case R.id.log_menu_view:
    	activityCallback.viewLogEntry(5);
        break;
    case R.id.log_menu_edit:
    	activityCallback.updateStatus("MENU EDIT");
        break;
	}
		
		Log.i(EVENT, "BUTTON CLICKED");
    }
	
   /* ____________________________________________
	*
	*	THIS AREA IS FOR ADDING SUPPORTING METHODS
	* ____________________________________________
    */
	
	public void resetFields() {
		
	}
	
}
