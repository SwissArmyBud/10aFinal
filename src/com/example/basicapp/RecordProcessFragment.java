package com.example.basicapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class RecordProcessFragment extends Fragment {

	Button mainButton;
    private static final String EVENT = "Event";
	
    RecordProcessFragInterface activityCallback;

    //CONTAINER ACTIVITY MUST IMPLEMENT THIS INTERFACE
    public interface RecordProcessFragInterface {
        public void updateStatus(String string);
    }

    //THIS SECTION ENSURES INTERFACE COMPLIANCE
    @Override
    public void onAttach(Activity activity) { 
        super.onAttach(activity);
        
        try {
            activityCallback = (RecordProcessFragInterface) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement RecordProcessFrag Interface");
        }
        
    }

    //THIS SECTION SETS BUTTON LISTENERS
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        
		//INFLATE THE LAYOUT FOR THIS FRAGMENT
        View rootView = inflater.inflate(R.layout.fragment_record_modify, container, false);
        
        activityCallback.updateStatus("***Adding Record***");
        
        return rootView;
    }
	
   /* ____________________________________________
	*
	*	THIS AREA IS FOR ADDING SUPPORTING METHODS
	* ____________________________________________
    */
	
	public void setFields(String string) {
		View rootView = getView().findViewById(R.id.main_text);
		EditText textBox = (EditText) rootView.findViewById(R.id.main_text);
		textBox.setHint(string);
		Log.i(EVENT, "RecordProcessFragment setFields complete");
	}
	
	public String getRecord(String string) {
		return "RecordProcessFragment getRecord";
	}
	
}
