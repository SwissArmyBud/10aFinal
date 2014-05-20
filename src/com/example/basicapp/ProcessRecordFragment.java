package com.example.basicapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class ProcessRecordFragment extends Fragment implements OnSeekBarChangeListener {

	SeekBar seekbar;
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
        View rootView = inflater.inflate(R.layout.process_record_fragment, container, false);
        
        //SET BUTTON CLICK LISTENERS
        seekbar = (SeekBar) rootView.findViewById(R.id.gas_seek_bar);
	    seekbar.setOnSeekBarChangeListener(this);
	   
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

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		View rootView = getView().findViewById(R.id.gas_text_box);
		TextView textBox = (TextView) rootView.findViewById(R.id.gas_text_box);
		textBox.setText("" + progress + "/8");
		Log.i(EVENT, "seekBarListener update: " + progress);
		
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}
}
