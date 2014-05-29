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

public class OCRProcessFragment extends Fragment implements OnClickListener {

	Button goNutsButton;
	
    private static final String EVENT = "Event";
    private static final String LIFE = "LifeCycle";
	
    GoNutsInterface activityCallback;

    //CONTAINER ACTIVITY MUST IMPLEMENT THIS INTERFACE
    public interface GoNutsInterface {
    	public void beginOCRTask();
    }

    //THIS SECTION ENSURES INTERFACE COMPLIANCE
    @Override
    public void onAttach(Activity activity) { 
        super.onAttach(activity);
		Log.i(LIFE, "GoNuts onAttach");
        
        try {
            activityCallback = (GoNutsInterface) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement GoNutsInterface");
        }
    }
    
    //THIS SECTION SETS BUTTON LISTENERS
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		Log.i(LIFE, "HomeFragment onCreateView");
        
		//INFLATE THE LAYOUT FOR THIS FRAGMENT
        View rootView = inflater.inflate(R.layout.ocr_activity, container, false);
        
        //SET BUTTON CLICK LISTENERS
        goNutsButton = (Button) rootView.findViewById(R.id.go_nuts_button);
        goNutsButton.setOnClickListener(this);
        
        //RETURN THE VIEW
        return rootView;
    }
	
	//THIS SECTION IS ACTIVATED WHEN A BUTTON IS CLICKED
	@Override  
    public void onClick(View v) { 
		Log.i(EVENT, "OCRProcessFragment onClick");
		
		switch (v.getId()) {
		
		//adding cases for different BUTTON ids
	    case R.id.go_nuts_button:
	    	Log.i(EVENT, "GONUTSMOTHEROFGOD()");
	    	activityCallback.beginOCRTask();
	        break;
	        
		}
		
    }
	
   /* ____________________________________________
	*
	*	THIS AREA IS FOR ADDING SUPPORTING METHODS
	* ____________________________________________
    */
	
}
