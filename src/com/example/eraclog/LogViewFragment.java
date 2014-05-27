package com.example.eraclog;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class LogViewFragment extends Fragment {

    private static final String EVENT = "Event";
    private static final String LIFE = "LifeCycle";
    
    ListView listView;  
    ArrayAdapter<String> listAdapter ;
    View lastTouchedView;
    private int lastPos = -1;

    LogUpdateStatus activityCallback;

    //CONTAINER ACTIVITY MUST IMPLEMENT THIS INTERFACE
    public interface LogUpdateStatus {
        public void updateStatus(String string);
    }

	//THIS SECTION ENSURES INTERFACE COMPLIANCE
    @Override
    public void onAttach(Activity activity) { 
        super.onAttach(activity);
		Log.i(LIFE, "ProcessRecordFragment onAttach");
        
        try {
            activityCallback = (LogUpdateStatus) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement UpdateStatus Interface");
        }
    }

    //THIS SECTION SETS BUTTON LISTENERS
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(LIFE, "LogViewFragment onCreateView()");
        Log.i(EVENT, "selection variable = " + lastPos);
        
		//INFLATE THE LAYOUT FOR THIS FRAGMENT
        View rootView = inflater.inflate(R.layout.log_view_fragment, container, false);
        
        //GET AND ATTACH A ListViewAdapter TO listView
        listView = (ListView) rootView.findViewById(R.id.log_list_view);
        final ListAdapter adapter = new ListAdapter(getActivity(), R.layout.list_adapter, MainActivity.getCurrentLog());
        listView.setAdapter(adapter);
        
        //LISTVIEW OnClick LISTENER FOR CLICK EVENTS
        listView.setOnItemClickListener(new OnItemClickListener()
            {
                    //THE position VARIABLE GIVES POSITION OF CLICKED ITEM IN LIST VIEW, STARTING WITH --> TOP ROW = 0
                    public void onItemClick(AdapterView<?> arg0, View v,int position, long arg3){
                    	Log.i(EVENT, "listView item " + position + " clicked");
                        
                        //SET ALL THE BACKGROUND COLORS TO THE SAME COLOR
                        int newPos = position;
                        for (int i=0;i<listView.getChildCount();i++){
                            listView.getChildAt(i).setBackgroundColor(Color.WHITE);
                        }
                        
                        //IF SAME ITEM IS CLICKED AS LAST TIME, MAKE IT WHITE TOO THEN REVERT THE lastPos VALUE TO -1
                        if (newPos==lastPos) {
                        	v.setBackgroundColor(Color.WHITE);
                        	lastPos = -1;
                        	Log.i(EVENT, "selection identical, " + position + "button unselected");
                        }
                        //OTHERWISE MAKE THE BUTTONS BACKGROUND A DIFFERENT COLOR AND STORE ITS POSITION USING lastPos
                        else {
                        	v.setBackgroundColor(Color.LTGRAY);
                        	Log.i(EVENT, "new selection, " + position + "button selected");
                        	lastPos = newPos;
                        }
                    }
                    
            });
        
        //RETURN THE VIEW
        return rootView;
    }
	
   /* ____________________________________________
	*
	*	THIS AREA IS FOR ADDING SUPPORTING METHODS
	* ____________________________________________
    */
	
	//THIS METHOD RETURNS A ZERO OR POSITIVE SELECTION OR -1 FOR ALL NEGATIVE NUMBERS
	public int hasSelection() {
		Log.i(EVENT, "hasSelection: " + lastPos);
		if (lastPos>-1) {
			return lastPos;
		} else {return -1;}
	}
}
