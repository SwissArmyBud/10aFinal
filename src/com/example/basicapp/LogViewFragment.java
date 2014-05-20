package com.example.basicapp;

import java.util.ArrayList;
import java.util.Arrays;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class LogViewFragment extends Fragment {

    private static final String EVENT = "Event";
    ListView listView;  
    ArrayAdapter<String> listAdapter ;  
    LogViewInterface activityCallback;

    //CONTAINER ACTIVITY MUST IMPLEMENT THIS INTERFACE
    public interface LogViewInterface {
        public void viewLogEntry(int i);
		public void updateStatus(String string);
    }
    

    //THIS SECTION ENSURES INTERFACE COMPLIANCE
    @Override
    public void onAttach(Activity activity) { 
        super.onAttach(activity);
        
        try {
            activityCallback = (LogViewInterface) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnButtonPush");
        }
        
    }

    //THIS SECTION SETS BUTTON LISTENERS
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        
		//INFLATE THE LAYOUT FOR THIS FRAGMENT
        View rootView = inflater.inflate(R.layout.log_view_fragment, container, false);
        
        // Get the reference of ListView
        listView = (ListView) rootView.findViewById(R.id.log_list_view);
        
        final ListAdapter adapter = new ListAdapter(getActivity(), R.layout.record_list_row, MainActivity.getCurrentLog());
         listView.setAdapter(adapter);
         // register onClickListener to handle click events on each item
         listView.setOnItemClickListener(new OnItemClickListener()
            {
                     // argument position gives the index of item which is clicked
                    public void onItemClick(AdapterView<?> arg0, View v,int position, long arg3){
                    	Log.i(EVENT, "listView item " + position + " clicked");
                    }
                    
            });
        
        activityCallback.updateStatus("***LOG VIEW***");
        
        return rootView;
    }
	
   /* ____________________________________________
	*
	*	THIS AREA IS FOR ADDING SUPPORTING METHODS
	* ____________________________________________
    */
	
	public void displayRecord(int i) {
		
	}
	
}
