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

public class RecordEditMenuFrag extends Fragment implements OnClickListener {

	private static final String EVENT = "Event";
    private static final String LIFE = "LifeCycle";

	Button saveButton;
	Button cancelButton;
	
    RecordEditMenuFragInterface activityCallback;

    //CONTAINER ACTIVITY MUST IMPLEMENT THIS INTERFACE
    public interface RecordEditMenuFragInterface {
        public void goToHomeScreen();
        public void saveRecordToLog();
    }

    //THIS SECTION ENSURES INTERFACE COMPLIANCE
    @Override
    public void onAttach(Activity activity) { 
        super.onAttach(activity);
		Log.i(LIFE, "RecordEditMenuFrag onAttach");
        
        try {
            activityCallback = (RecordEditMenuFragInterface) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement RecordEditMenuFragInterface");
        }
        
    }

    //THIS SECTION SETS BUTTON LISTENERS
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		Log.i(LIFE, "RecordEditMenuFrag onCreateView");
        
		//INFLATE THE LAYOUT FOR THIS FRAGMENT
        View rootView = inflater.inflate(R.layout.record_edit_menu, container, false);
        
        //SET BUTTON CLICK LISTENERS
        cancelButton = (Button) rootView.findViewById(R.id.record_edit_cancel_button);
        cancelButton.setOnClickListener(this); 
        saveButton = (Button) rootView.findViewById(R.id.record_edit_save_button);
        saveButton.setOnClickListener(this);
        
        return rootView;
    }
	
	//THIS SECTION IS ACTIVATED WHEN A BUTTON IS CLICKED
	@Override  
    public void onClick(View v) { 
        Log.i(LIFE, "RecordEditMenuFrag onClick");
		
		switch (v.getId()) {
		
		//ADDING CASES FOR DIFFERENT BUTTON IDS
		case R.id.record_edit_save_button:
	    	Log.i(EVENT, "RecordEditMenuFrag calling saveRecordToLog()");
			activityCallback.saveRecordToLog();
			break;
		case R.id.record_edit_cancel_button:
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
