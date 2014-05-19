package com.example.basicapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class HomeFragment extends Fragment implements OnClickListener {

	Button addRecordButton;
	Button viewLogButton;
	Button saveLogButton;
	
    private static final String EVENT = "Event";
    private static final String LIFECYCLE = "LifeCycle";
	
    HomeFragmentInterface activityCallback;

    //CONTAINER ACTIVITY MUST IMPLEMENT THIS INTERFACE
    public interface HomeFragmentInterface {
        public void homeAddRecordButtonPush();
        public void homeViewLogButton();
        public void homeSaveLogButton();
        public void updateStatus(String string);
    }

    //THIS SECTION ENSURES INTERFACE COMPLIANCE
    @Override
    public void onAttach(Activity activity) { 
        super.onAttach(activity);
        
        try {
            activityCallback = (HomeFragmentInterface) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnButtonPush");
        }
        
    }

    //THIS SECTION SETS BUTTON LISTENERS
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        
		//INFLATE THE LAYOUT FOR THIS FRAGMENT
        View rootView = inflater.inflate(R.layout.home_fragment, container, false);
        
        //SET BUTTON CLICK LISTENERS
        addRecordButton = (Button) rootView.findViewById(R.id.add_record_button);
        addRecordButton.setOnClickListener(this); 
        viewLogButton = (Button) rootView.findViewById(R.id.view_log_button);
        viewLogButton.setOnClickListener(this); 
        saveLogButton = (Button) rootView.findViewById(R.id.save_log_button);
        saveLogButton.setOnClickListener(this); 
        Log.i(EVENT, "HomeFragment button click listeners set");

    	activityCallback.updateStatus("***HOME SCREEN***");
        
        return rootView;
    }
	
	//THIS SECTION IS ACTIVATED WHEN A BUTTON IS CLICKED
	@Override  
    public void onClick(View v) { 
		
		switch (v.getId()) {
		
		//adding cases for different BUTTON ids
	    case R.id.add_record_button:
	    	Log.i(EVENT, "HomeFrag calling addRecord()");
	    	activityCallback.homeAddRecordButtonPush();
	        break;
	    case R.id.view_log_button:
	    	Log.i(EVENT, "HomeFrag calling viewLog()");
	    	activityCallback.homeViewLogButton();
	        break;
	    case R.id.save_log_button:
	    	Log.i(EVENT, "HomeFrag calling viewLog()");
	    	activityCallback.homeSaveLogButton();
	        break;
	        
		}
		
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
