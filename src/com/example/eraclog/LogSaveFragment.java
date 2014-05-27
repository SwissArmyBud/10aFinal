package com.example.eraclog;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;

public class LogSaveFragment extends Fragment implements OnClickListener {

	Button saveLogButton;
	Button loadLogButton;
	Button cancelButton;
	
    private static final String EVENT = "Event";
    private static final String LIFE = "LifeCycle";
	
    SaveLogFragInterface activityCallback;

    //CONTAINER ACTIVITY MUST IMPLEMENT THIS INTERFACE
    public interface SaveLogFragInterface {
        public void saveLoadLogButton();
        public void saveSaveLogButton();
        public void saveClearLogButton();
        public void goToHomeScreen();
    }

    //THIS SECTION ENSURES INTERFACE COMPLIANCE
    @Override
    public void onAttach(Activity activity) { 
        super.onAttach(activity);
		Log.i(LIFE, "HomeFragment onAttach");
        
        try {
            activityCallback = (SaveLogFragInterface) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement SaveLogFragInterface");
        }
    }

    //THIS SECTION SETS BUTTON LISTENERS
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		Log.i(LIFE, "HomeFragment onCreateView");
        
		//INFLATE THE LAYOUT FOR THIS FRAGMENT
        View rootView = inflater.inflate(R.layout.log_save_menu, container, false);
        
        
        Button saveLogButton;
    	Button loadLogButton;
    	Button clearLogButton;
    	Button cancelButton;
        
        //SET BUTTON CLICK LISTENERS
    	saveLogButton = (Button) rootView.findViewById(R.id.log_save_button);
    	saveLogButton.setOnClickListener(this); 
    	loadLogButton = (Button) rootView.findViewById(R.id.log_load_button);
    	loadLogButton.setOnClickListener(this); 
    	clearLogButton = (Button) rootView.findViewById(R.id.log_clear_button);
    	clearLogButton.setOnClickListener(this); 
    	cancelButton = (Button) rootView.findViewById(R.id.log_cancel_button);
    	cancelButton.setOnClickListener(this);
    	
        //RETURN THE VIEW
        return rootView;
    }
	
	//THIS SECTION IS ACTIVATED WHEN A BUTTON IS CLICKED
	@Override  
    public void onClick(View v) { 
		Log.i(LIFE, "HomeFragment onClick");
		
		switch (v.getId()) {
		
		//adding cases for different BUTTON ids
	    case R.id.log_load_button:
	    	Log.i(EVENT, "SaveLog calling saveLoadLogButton()");
	    	activityCallback.saveLoadLogButton();
	        break;
	    case R.id.log_save_button:
	    	Log.i(EVENT, "SaveLog calling saveSaveLogButton()");
	    	activityCallback.saveSaveLogButton();
	        break;
	    case R.id.log_clear_button:
	    	Log.i(EVENT, "SaveLog calling saveClearLogButton()");
			activityCallback.saveClearLogButton();
	        break;
	    case R.id.log_cancel_button:
	    	Log.i(EVENT, "SaveLog calling goToHomeScreen()");
			activityCallback.goToHomeScreen();
	        break;
	        
		}
		
    }
	
   /* ____________________________________________
	*
	*	THIS AREA IS FOR ADDING SUPPORTING METHODS
	* ____________________________________________
    */
	
}
