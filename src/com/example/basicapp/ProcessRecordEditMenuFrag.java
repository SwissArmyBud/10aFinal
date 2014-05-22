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

public class ProcessRecordEditMenuFrag extends Fragment implements OnClickListener {

    @SuppressWarnings("unused")
	private static final String EVENT = "Event";
    private static final String LIFE = "LifeCycle";

	Button saveButton;
	Button cancelButton;
	
    ProcessRecordEditMenuFragInterface activityCallback;

    //CONTAINER ACTIVITY MUST IMPLEMENT THIS INTERFACE
    public interface ProcessRecordEditMenuFragInterface {
        public void editCancel();
        public void saveRecordButtonPush();
    }

    //THIS SECTION ENSURES INTERFACE COMPLIANCE
    @Override
    public void onAttach(Activity activity) { 
        super.onAttach(activity);
		Log.i(LIFE, "ProcessRecordEditMenuFrag onAttach");
        
        try {
            activityCallback = (ProcessRecordEditMenuFragInterface) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement ProcessRecordMenuFragInterface");
        }
        
    }

    //THIS SECTION SETS BUTTON LISTENERS
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		Log.i(LIFE, "ProcessRecordEditMenuFrag onCreateView");
        
		//INFLATE THE LAYOUT FOR THIS FRAGMENT
        View rootView = inflater.inflate(R.layout.process_record_edit_menu, container, false);
        
        //SET BUTTON CLICK LISTENERS
        cancelButton = (Button) rootView.findViewById(R.id.record_cancel);
        cancelButton.setOnClickListener(this); 
        saveButton = (Button) rootView.findViewById(R.id.save_record_button);
        saveButton.setOnClickListener(this);
        
        return rootView;
    }
	
	//THIS SECTION IS ACTIVATED WHEN A BUTTON IS CLICKED
	@Override  
    public void onClick(View v) { 
		
		switch (v.getId()) {
		
		//ADDING CASES FOR DIFFERENT BUTTON IDS
		case R.id.save_record_button:
			activityCallback.saveRecordButtonPush();
			break;
		case R.id.record_cancel:
			activityCallback.editCancel();
			break;
		}
    }
	
   /* ____________________________________________
	*
	*	THIS AREA IS FOR ADDING SUPPORTING METHODS
	* ____________________________________________
    */
	
}
