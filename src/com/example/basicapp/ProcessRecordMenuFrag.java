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

public class ProcessRecordMenuFrag extends Fragment implements OnClickListener {

	Button addButton;
	Button resetButton;

    private static final String EVENT = "Event";
	
    ProcessRecordMenuFragInterface activityCallback;

    //CONTAINER ACTIVITY MUST IMPLEMENT THIS INTERFACE
    public interface ProcessRecordMenuFragInterface {
        public void commitRecordButtonPush();
    }

    //THIS SECTION ENSURES INTERFACE COMPLIANCE
    @Override
    public void onAttach(Activity activity) { 
        super.onAttach(activity);
        
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
        
		//INFLATE THE LAYOUT FOR THIS FRAGMENT
        View rootView = inflater.inflate(R.layout.record_process_menu, container, false);
        
        //SET BUTTON CLICK LISTENERS
        addButton = (Button) rootView.findViewById(R.id.commit_record_button);
        addButton.setOnClickListener(this); 
        resetButton = (Button) rootView.findViewById(R.id.reset_record_button);
        resetButton.setOnClickListener(this); 
        
        return rootView;
    }
	
	//THIS SECTION IS ACTIVATED WHEN A BUTTON IS CLICKED
	@Override  
    public void onClick(View v) { 
		
		switch (v.getId()) {
		
		//adding cases for different BUTTON ids
    case R.id.commit_record_button:
    	activityCallback.commitRecordButtonPush();
        break;
    case R.id.reset_record_button:
    	resetFields();
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
