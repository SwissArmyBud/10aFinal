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

public class HomeFragment extends Fragment implements OnClickListener {

	Button addRecordButton;
	Button viewLogButton;
	Button manageLogButton;
	Button goNutsButton;
	
    private static final String EVENT = "Event";
    private static final String LIFE = "LifeCycle";
	
    HomeFragmentInterface activityCallback;

    //CONTAINER ACTIVITY MUST IMPLEMENT THIS INTERFACE
    public interface HomeFragmentInterface {
        public void homeAddRecordButton();
        public void homeViewLogButton();
        public void homeSaveLogButton();
        public void goNuts();
    }

    //THIS SECTION ENSURES INTERFACE COMPLIANCE
    @Override
    public void onAttach(Activity activity) { 
        super.onAttach(activity);
		Log.i(LIFE, "HomeFragment onAttach");
        
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
		Log.i(LIFE, "HomeFragment onCreateView");
        
		//INFLATE THE LAYOUT FOR THIS FRAGMENT
        View rootView = inflater.inflate(R.layout.home_fragment, container, false);
        
        //SET BUTTON CLICK LISTENERS
        addRecordButton = (Button) rootView.findViewById(R.id.home_add_record_button);
        addRecordButton.setOnClickListener(this); 
        viewLogButton = (Button) rootView.findViewById(R.id.home_view_log_button);
        viewLogButton.setOnClickListener(this); 
        manageLogButton = (Button) rootView.findViewById(R.id.home_manage_log_button);
        manageLogButton.setOnClickListener(this);
        goNutsButton = (Button) rootView.findViewById(R.id.go_nuts);
        goNutsButton.setOnClickListener(this);
        
        //RETURN THE VIEW
        return rootView;
    }
	
	//THIS SECTION IS ACTIVATED WHEN A BUTTON IS CLICKED
	@Override  
    public void onClick(View v) { 
		Log.i(EVENT, "HomeFragment onClick");
		
		switch (v.getId()) {
		
		//adding cases for different BUTTON ids
	    case R.id.home_add_record_button:
	    	Log.i(EVENT, "HomeFrag calling homeAddRecordButton()");
	    	activityCallback.homeAddRecordButton();
	        break;
	    case R.id.home_view_log_button:
	    	Log.i(EVENT, "HomeFrag calling homeViewLogButton()");
	    	activityCallback.homeViewLogButton();
	        break;
	    case R.id.home_manage_log_button:
	    	Log.i(EVENT, "HomeFrag calling homeSaveLogButton()");
			activityCallback.homeSaveLogButton();
	        break;
	    case R.id.go_nuts:
	    	Log.i(EVENT, "HomeFrag calling goNuts()");
			activityCallback.goNuts();
	        break;
	        
		}
		
    }
	
   /* ____________________________________________
	*
	*	THIS AREA IS FOR ADDING SUPPORTING METHODS
	* ____________________________________________
    */
	
}
