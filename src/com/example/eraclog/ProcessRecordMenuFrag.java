package com.example.eraclog;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class ProcessRecordMenuFrag extends Fragment implements OnClickListener {

	private static final String EVENT = "Event";
    private static final String LIFE = "LifeCycle";

	Button addButton;
	Button resetButton;
	Button cancelButton;
	
    ProcessRecordMenuFragInterface activityCallback;

    //CONTAINER ACTIVITY MUST IMPLEMENT THIS INTERFACE
    public interface ProcessRecordMenuFragInterface {
        public void saveRecordToLog();
        public void goToHomeScreen();
    }

    //THIS SECTION ENSURES INTERFACE COMPLIANCE
    @Override
    public void onAttach(Activity activity) { 
        super.onAttach(activity);
		Log.i(LIFE, "ProcessRecordMenuFrag onAttach");
        
        try {
            activityCallback = (ProcessRecordMenuFragInterface) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement ProcessRecordMenuFragInterface");
        }
        
    }

    //THIS SECTION SETS BUTTON LISTENERS
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		Log.i(LIFE, "ProcessRecordMenuFrag onCreateView");
        
		//INFLATE THE LAYOUT FOR THIS FRAGMENT
        View rootView = inflater.inflate(R.layout.process_record_menu, container, false);
        
        //SET BUTTON CLICK LISTENERS
        cancelButton = (Button) rootView.findViewById(R.id.process_record_cancel_button);
        cancelButton.setOnClickListener(this); 
        addButton = (Button) rootView.findViewById(R.id.process_record_add_button);
        addButton.setOnClickListener(this);
        
        return rootView;
    }
	
	//THIS SECTION IS ACTIVATED WHEN A BUTTON IS CLICKED
	@Override  
    public void onClick(View v) { 
		
		switch (v.getId()) {
		
		//ADDING CASES FOR DIFFERENT BUTTON IDS
		case R.id.process_record_add_button:
	    	Log.i(EVENT, "ProcessRecordFrag calling saveRecordToLog()");
			activityCallback.saveRecordToLog();
			break;
		case R.id.process_record_cancel_button:
	    	Log.i(EVENT, "RecordEditMenuFrag calling goToHomeScreen()");
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
