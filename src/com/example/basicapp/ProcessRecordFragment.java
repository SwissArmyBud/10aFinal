package com.example.basicapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class ProcessRecordFragment extends Fragment implements OnSeekBarChangeListener {

    private static final String EVENT = "Event";
    private static final String LIFE = "LifeCycle";
    
	Record[] washLog;
	SeekBar seekbar;
	int selection;
	
    UpdateStatus activityCallback;

    //CONTAINER ACTIVITY MUST IMPLEMENT THIS INTERFACE
    public interface UpdateStatus {
        public void updateStatus(String string);
    }

	//THIS SECTION ENSURES INTERFACE COMPLIANCE
    @Override
    public void onAttach(Activity activity) { 
        super.onAttach(activity);
		Log.i(LIFE, "ProcessRecordFragment onAttach");
        
        try {
            activityCallback = (UpdateStatus) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement UpdateStatus Interface");
        }
    }

    //THIS SECTION SETS BUTTON LISTENERS
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		Log.i(LIFE, "ProcessRecordFragment onCreateView");
        
		//INFLATE THE LAYOUT FOR THIS FRAGMENT
        View rootView = inflater.inflate(R.layout.process_record_fragment, container, false);
        
        //SET SEEK LISTENER
        seekbar = (SeekBar) rootView.findViewById(R.id.gas_seek_bar);
	    seekbar.setOnSeekBarChangeListener(this);
	    
	    //RETURN THE VIEW
        return rootView;
    }
	
   /* ____________________________________________
	*
	*	THIS AREA IS FOR ADDING SUPPORTING METHODS
	* ____________________________________________
    */
	
	//REQUIRED METHOD FROM SCROLL HANDLER
	@Override
	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
		Log.i(EVENT, "seekBarListener update: " + progress);
		
		View rootView = getView().findViewById(R.id.gas_text_box);
		TextView textBox = (TextView) rootView.findViewById(R.id.gas_text_box);
		textBox.setText("" + progress + "/8");
		
	}
	
	//REQUIRED METHOD FROM SCROLL HANDLER
	@Override  
	public void onStartTrackingTouch(SeekBar seekBar) {
		Log.i(EVENT, "seekBarListener onStartTrackingTouch");
		//NO USE FOR THIS METHOD SO FAR
	}

	//REQUIRED METHOD FROM SCROLL HANDLER
	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		Log.i(EVENT, "seekBarListener onStopTrackingTouch");
		//NO USE FOR THIS METHOD SO FAR
		
	}
}
